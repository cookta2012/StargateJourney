package net.povstalec.sgjourney.common.compatibility.cctweaked.peripherals;

import java.util.LinkedList;
import java.util.List;

import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraftforge.common.util.LazyOptional;
import net.povstalec.sgjourney.common.block_entities.TransceiverEntity;

public class TransceiverPeripheralWrapper
{
	private TransceiverEntity transceiver;
	private TransceiverBlockPeripheral transceiverBlockPeripheral;
	private LazyOptional<IPeripheral> peripheral;
    protected final List<IComputerAccess> computerList = new LinkedList<>();
	
	public TransceiverPeripheralWrapper(TransceiverEntity transceiver)
	{
		this.transceiver = transceiver;
	}
	
	public static TransceiverBlockPeripheral createPeripheral(TransceiverEntity transceiver)
	{
		return new TransceiverBlockPeripheral(transceiver);
	}
	
	public LazyOptional<IPeripheral> newPeripheral()
	{
		transceiverBlockPeripheral = createPeripheral(transceiver);
		peripheral = LazyOptional.of(() -> transceiverBlockPeripheral);
		
		return peripheral;
	}
	
	public void queueEvent(String eventName, Object... objects)
	{
		if(transceiverBlockPeripheral != null)
			transceiverBlockPeripheral.queueEvent(eventName, objects);
	}
	
	public TransceiverBlockPeripheral getPeripheral()
	{
		return this.transceiverBlockPeripheral;
	}
}
