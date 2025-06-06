package net.povstalec.sgjourney.common.block_entities.tech;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.povstalec.sgjourney.common.block_entities.StructureGenEntity;
import org.jetbrains.annotations.NotNull;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.povstalec.sgjourney.StargateJourney;
import net.povstalec.sgjourney.common.block_entities.EnergyBlockEntity;
import net.povstalec.sgjourney.common.config.StargateJourneyConfig;
import net.povstalec.sgjourney.common.data.TransporterNetwork;

public abstract class AbstractTransporterEntity extends EnergyBlockEntity implements StructureGenEntity, Nameable
{
	protected static final boolean requireEnergy = !StargateJourneyConfig.disable_energy_use.get();
	
	public static final String ID = "ID";
	public static final String CUSTOM_NAME = "CustomName";
	
	protected StructureGenEntity.Step generationStep = Step.GENERATED;
	
	protected UUID id;
	protected String connectionID = StargateJourney.EMPTY;

	@Nullable
	private Component name;
	
	public AbstractTransporterEntity(BlockEntityType<?> type, BlockPos pos, BlockState state)
	{
		super(type, pos, state);
	}
	
	@Override
    public void onLoad()
	{
		super.onLoad();
		
        if(level.isClientSide())
	        return;
		
		if(generationStep == StructureGenEntity.Step.READY)
			generate();
	}
	
	@Override
	public void load(CompoundTag tag)
	{
		super.load(tag);
		
		if(tag.contains(GENERATION_STEP, CompoundTag.TAG_BYTE))
			generationStep = StructureGenEntity.Step.fromByte(tag.getByte(GENERATION_STEP));
		
		try
		{
    		if(tag.contains(ID))
        		id = UUID.fromString(tag.getString(ID));
		}
		catch(IllegalArgumentException e)
		{
			this.setID(this.generateID());
		}
    	
    	if(tag.contains(CUSTOM_NAME, 8))
	         this.name = Component.Serializer.fromJson(tag.getString(CUSTOM_NAME));
	}
	
	@Override
	protected void saveAdditional(@NotNull CompoundTag tag)
	{
		if(generationStep != Step.GENERATED)
			tag.putByte(GENERATION_STEP, generationStep.byteValue());
		
		if(id != null)
			tag.putString(ID, id.toString());
		
		super.saveAdditional(tag);
		
		if(this.name != null)
	         tag.putString(CUSTOM_NAME, Component.Serializer.toJson(this.name));
	}
	
	public UUID generateID()
	{
		return UUID.randomUUID();
	}
	
	public void setID(UUID id)
	{
    	this.id = id;
		setChanged();
		StargateJourney.LOGGER.info("Set ID to " + id);
	}
	
	public UUID getID()
	{
		return id;
	}
	
	public void addTransporterToNetwork()
	{
		if(this.id == null)
			setID(generateID());
		
		TransporterNetwork.get(level).addTransporter(this);
		this.setChanged();
	}
	
	public void removeTransporterFromNetwork()
	{
		TransporterNetwork.get(level).removeTransporter(level, this.id);
	}
	
	@Override
	public void getStatus(Player player)
	{
		super.getStatus(player);
		
		if(level.isClientSide())
			return;
		
		player.sendSystemMessage(Component.literal("ID: " + id).withStyle(ChatFormatting.AQUA));
		player.sendSystemMessage(Component.translatable("info.sgjourney.add_to_network").append(Component.literal(": " + (generationStep == Step.GENERATED))).withStyle(ChatFormatting.YELLOW));
	}
	
	public void setCustomName(Component name)
	{
		this.name = name;
	}
	
	public Component getName()
	{
		return this.name != null ? this.name : this.getDefaultName();
	}
	
	public Component getDisplayName()
	{
		return this.getName();
	}
	
	@Nullable
	public Component getCustomName()
	{
		return this.name;
	}
	
	protected abstract Component getDefaultName();
	
	
	
	public abstract boolean isConnected();
	
	public int getTimeOffset()
	{
		return 0;
	}
	
	//============================================================================================
	//*****************************************Generation*****************************************
	//============================================================================================
	
	@Override
	public void generateInStructure(WorldGenLevel level, RandomSource randomSource)
	{
		if(generationStep == Step.SETUP)
			generationStep = Step.READY;
	}
	
	public void generate()
	{
		addTransporterToNetwork();
		
		generationStep = Step.GENERATED;
	}
}
