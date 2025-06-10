package net.povstalec.sgjourney.common.compatibility.cctweaked.peripherals;

import com.google.errorprone.annotations.concurrent.GuardedBy;
import dan200.computercraft.api.lua.*;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.pocket.IPocketAccess;
import net.povstalec.sgjourney.common.compatibility.computer_functions.TransceiverFunctions;
import net.povstalec.sgjourney.common.handlers.TransceiverState;
import net.povstalec.sgjourney.common.sgjourney.ITransciever;
import net.povstalec.sgjourney.common.sgjourney.ITransmissionReceiver;
import net.povstalec.sgjourney.common.sgjourney.IPortableTransmissionSender;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseTransceiverPeripheral implements IPeripheral, IPortableTransmissionSender, ITransmissionReceiver, ITransciever
{
	private static final String EVENT_TRANSMISSION_RECEIVED = "transceiver_transmission_received";
	protected TransceiverState state;
	private final @GuardedBy("computers") Set<IComputerAccess> computers = new HashSet<>(1);

	//protected HashMap<String, TransceiverMethod> methods = new HashMap<String, TransceiverMethod>(1);

	public BaseTransceiverPeripheral(IPocketAccess access, TransceiverState state)
	{
		this.state = state;
	}

	@Override
	public String getType()
	{
		return "transceiver";
	}


    @Override
    public synchronized void attach(@Nonnull IComputerAccess computer)
    {
    	computers.add(computer);
    }

    @Override
    public synchronized void detach(@Nonnull IComputerAccess computer)
    {
		computers.remove(computer);;
    }

	/**
	 * Determine whether this peripheral is equivalent to another one.
	 * <p>
	 * The minimal example should at least check whether they are the same object. However, you may wish to check if
	 * they point to the same block or tile entity.
	 *
	 * @param other The peripheral to compare against. This may be {@code null}.
	 * @return Whether these peripherals are equivalent.
	 */
	@Override
	public boolean equals(IPeripheral other)
	{
		if(this == other)
			return true;
		return false;
		//return this.getClass() == other.getClass() && this.transceiverEntity == ((TransceiverBlockPeripheral) other).transceiverEntity;
	}

	@Override
	public int getFrequency() {
		return state.getFrequency();
	}

	@Override
	public String getCurrentCode() {
		return state.getCurrentCode();
	}

	@Override
	public void setFrequency(int frequency) {
		state.setFrequency(frequency);
	}

	@Override
	public void setCurrentCode(String code) {
		state.setCurrentCode(code);
	}

	/**
	 * @return
	 */
	@Override
	public int checkShieldingState() {
		return -1;
	}

	/**
	 * @param transmissionJump
	 * @param frequency
	 * @param transmission
	 */
	@Override
	public void receiveTransmission(int transmissionJump, int frequency, String transmission) {
		if(getLevel().isClientSide() || frequency != getFrequency())
			return;

		boolean codeIsCorrect = getCurrentCode().equals(transmission);

		queueEvent(EVENT_TRANSMISSION_RECEIVED, frequency, transmission, codeIsCorrect);
	}

	public void queueEvent(String eventName, Object... objects)
	{
		for(IComputerAccess computer : computers)
		{
			int length = objects.length + 1;
			Object[] attachmentObjects = new Object[length];

			attachmentObjects[0] = computer.getAttachmentName();

			for(int i = 1; i < length; i++)
			{
				attachmentObjects[i] = objects[i - 1];
			}

			computer.queueEvent(eventName, attachmentObjects);
		}
	}

	/*
	public <ConnectedBlockEntity extends BlockEntity> void registerMethod(TransceiverMethod function)
	{
		methods.put(function.getName(), function);
	}
	 */
	
	//============================================================================================
	//*************************************CC: Tweaked Events*************************************
	//============================================================================================
	/*
	public void queueEvent(String eventName, Object... objects)
	{
		for(IComputerAccess computer : transceiverEntity.getPeripheralWrapper().computerList)
		{
			int length = objects.length + 1;
			Object[] attachmentObjects = new Object[length];
			
			attachmentObjects[0] = computer.getAttachmentName();
			
			for(int i = 1; i < length; i++)
			{
				attachmentObjects[i] = objects[i - 1];
			}
			
			computer.queueEvent(eventName, attachmentObjects);
		}
	}
	
	//============================================================================================
	//*****************************************CC: Tweaked****************************************
	//============================================================================================

	@LuaFunction(mainThread = true)
	public final int getFrequency() throws LuaException
	{
		return TransceiverFunctions.getFrequency(transceiverEntity);
	}

	@LuaFunction(mainThread = true)
	public final String getCurrentCode() throws LuaException
	{
		return TransceiverFunctions.getCurrentCode(transceiverEntity);
	}
	
	 */
}
