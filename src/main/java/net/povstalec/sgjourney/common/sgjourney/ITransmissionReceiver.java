package net.povstalec.sgjourney.common.sgjourney;

public interface ITransmissionReceiver extends ITranscieverLocation
{
	void receiveTransmission(int transmissionJump, int frequency, String transmission);
}
