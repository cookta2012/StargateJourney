package net.povstalec.sgjourney.common.compatibility.computer_functions;

import net.povstalec.sgjourney.common.block_entities.TransceiverEntity;
import net.povstalec.sgjourney.common.compatibility.cctweaked.peripherals.BaseTransceiverPeripheral;
import net.povstalec.sgjourney.common.handlers.TransceiverState;

public class NewTransceiverFunctions
{
	public static int getFrequency(TransceiverState state)
	{
		return state.getFrequency();
	}
	
	public static void setFrequency(TransceiverState state, int frequency)
	{
		state.setFrequency(frequency);
	}
	
	public static String getCurrentCode(TransceiverState state)
	{
		return state.getCurrentCode();
	}
	
	public static void setCurrentCode(TransceiverState state, String idc)
	{
		state.setCurrentCode(idc);
	}
	
	public static void sendTransmission(BaseTransceiverPeripheral transceiver)
	{
		transceiver.sendTransmission();
	}


	public static Integer checkConnectedShielding(BaseTransceiverPeripheral transceiver)
	{
		/*

		int state;
		if(transceiver instanceof TransceiverEntity entity) {
			state = entity.checkShieldingState();

			if (state < 0)
				return null;
		}

		 */
		return null;
	}
}
