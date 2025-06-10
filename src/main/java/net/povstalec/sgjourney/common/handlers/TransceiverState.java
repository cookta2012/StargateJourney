package net.povstalec.sgjourney.common.handlers;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;


public class TransceiverState
{
	public static final String IDC = "idc";
	public static final String FREQUENCY = "frequency";
	public static final String EDIT_FREQUENCY = "edit_frequency";

	private final @Nullable Runnable onChanged;

	private static final short MAX_TRANSMISSION_TICKS = 20;

	private final AtomicBoolean editingFrequency = new AtomicBoolean(false);
	private final AtomicBoolean changed = new AtomicBoolean(true);
	private final AtomicBoolean transmitting = new AtomicBoolean(false);
	private int frequency = 0;
	private String idc = "";

	//private short transmissionTicks = 0;

	public TransceiverState() {
		onChanged = null;
	}
	public TransceiverState(@Nullable Runnable onChanged){
		this.onChanged = onChanged;
	}

	private void update(){
		if (this.onChanged != null) this.onChanged.run();
	}

	public void setFrequency(int frequency)
	{
		this.frequency = frequency;
		changed.set(true);
	}

	public int getFrequency()
	{
		return frequency;
	}

	public void setEditingFrequency(boolean editingFrequency)
	{
		this.editingFrequency.set(editingFrequency);;
	}

	public boolean editingFrequency()
	{
		return editingFrequency.get();
	}

	public void setCurrentCode(String idc)
	{
		this.idc = idc;
		changed.set(true);
	}

	public String getCurrentCode()
	{
		return idc;
	}

	public boolean hasChanged(){
		return changed.getAndSet(false);
	}

	public void load(@NotNull CompoundTag tag)
	{
		editingFrequency.set(tag.getBoolean(EDIT_FREQUENCY));
		frequency = tag.getInt(FREQUENCY);
		idc = tag.getString(IDC);
	}

	public void save(@NotNull CompoundTag tag)
	{
		tag.putBoolean(EDIT_FREQUENCY, editingFrequency.get());
		tag.putInt(FREQUENCY, frequency);
		tag.putString(IDC, idc);
	}

}
