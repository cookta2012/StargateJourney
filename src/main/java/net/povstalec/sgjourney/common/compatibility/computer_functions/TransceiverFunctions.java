package net.povstalec.sgjourney.common.compatibility.computer_functions;

import net.povstalec.sgjourney.common.sgjourney.ITransciever;

public class TransceiverFunctions
{
	public static int getFrequency(ITransciever transceiver)
	{
		return transceiver.getFrequency();
	}
	
	public static void setFrequency(ITransciever transceiver, int frequency)
	{
		transceiver.setFrequency(frequency);
	}
	
	public static String getCurrentCode(ITransciever transceiver)
	{
		return transceiver.getCurrentCode();
	}
	
	public static void setCurrentCode(ITransciever transceiver, String idc)
	{
		transceiver.setCurrentCode(idc);
	}
	
	public static void sendTransmission(ITransciever transceiver)
	{
		transceiver.sendTransmission();
	}

	public static Integer checkConnectedShielding(ITransciever transceiver)
	{
		int state = transceiver.checkShieldingState();
		
		if(state < 0)
			return null;
		
		return state;
	}
}
