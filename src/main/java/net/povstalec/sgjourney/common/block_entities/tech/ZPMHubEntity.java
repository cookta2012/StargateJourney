package net.povstalec.sgjourney.common.block_entities.tech;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.povstalec.sgjourney.common.block_entities.ProtectedBlockEntity;
import net.povstalec.sgjourney.common.config.CommonPermissionConfig;
import net.povstalec.sgjourney.common.handlers.ProtectedItemStackHandler;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.povstalec.sgjourney.common.block_entities.EnergyBlockEntity;
import net.povstalec.sgjourney.common.capabilities.SGJourneyEnergy;
import net.povstalec.sgjourney.common.capabilities.ZeroPointEnergy;
import net.povstalec.sgjourney.common.config.CommonZPMConfig;
import net.povstalec.sgjourney.common.init.BlockEntityInit;
import net.povstalec.sgjourney.common.init.ItemInit;

public class ZPMHubEntity extends EnergyBlockEntity implements ProtectedBlockEntity
{
	public static final String PROTECTED = "protected";
	private static final long maxTransfer = CommonZPMConfig.zpm_hub_max_transfer.get();
	private static final long maxEnergyDisplayed = CommonZPMConfig.zpm_energy_per_level_of_entropy.get();
	
	protected final ProtectedItemStackHandler pItemStackHandler;
	private final LazyOptional<IItemHandler> lazyItemHandler;
	private final LazyOptional<IItemHandler> lazyProtectedItemHandler;
	private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();
	protected boolean isProtected = false;

	public ZPMHubEntity(BlockPos pos, BlockState state)
	{
		super(BlockEntityInit.ZPM_HUB.get(), pos, state);
		pItemStackHandler = new ProtectedItemStackHandler(1,this::isProtected);
		pItemStackHandler.unprotect().set_onContentsChanged((slots)->{
				this.setChanged();
			})
				.set_isItemValid((slot, stack)->{
				switch(slot)
					{
						case 0:
						return stack.getItem() == ItemInit.ZPM.get();
				default:
						return false;
			}
		});

		lazyProtectedItemHandler = LazyOptional.of(()-> pItemStackHandler);
		lazyItemHandler = LazyOptional.of(pItemStackHandler::unprotect);
	}
	
	@Override
	public void invalidateCaps()
	{
		super.invalidateCaps();
		lazyItemHandler.invalidate();
		lazyProtectedItemHandler.invalidate();
		lazyEnergyHandler.invalidate();
	}
	
	@Override
	public void load(CompoundTag nbt)
	{
		super.load(nbt);
		pItemStackHandler.deserializeNBT(nbt.getCompound("Inventory"));
		if(nbt.contains(PROTECTED, CompoundTag.TAG_BYTE))
			isProtected = nbt.getBoolean(PROTECTED);
	}
	
	@Override
	protected void saveAdditional(@NotNull CompoundTag nbt)
	{
		super.saveAdditional(nbt);
		nbt.put("Inventory", pItemStackHandler.serializeNBT());
		if(isProtected)
			nbt.putBoolean(PROTECTED, true);
	}
	
	//============================================================================================
	//****************************************Capabilities****************************************
	//============================================================================================
	
	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side)
	{
		if(capability == ForgeCapabilities.ITEM_HANDLER){
			if(side != null) // automation passes a side 99.9% of times
				return lazyProtectedItemHandler.cast();
			else // User trying to access by hand
				return lazyItemHandler.cast();
		}
		return super.getCapability(capability, side);
	}
	
	//============================================================================================
	//******************************************Storage*******************************************
	//============================================================================================
	
	public void drops()
	{
		SimpleContainer inventory = new SimpleContainer(pItemStackHandler.getSlots());
		for (int i = 0; i < pItemStackHandler.getSlots(); i++)
		{
			inventory.setItem(i, pItemStackHandler.getStackInSlot(i));
		}
		
		Containers.dropContents(this.level, this.worldPosition, inventory);
	}
	
	//============================================================================================
	//*******************************************Energy*******************************************
	//============================================================================================
	
	@Override
	public boolean isCorrectEnergySide(Direction side)
	{
		return side == Direction.DOWN;
	}
	
	protected boolean receivesEnergy()
	{
		return false;
	}
	
	@Override
	public long capacity()
	{
		return maxEnergyDisplayed;
	}

	@Override
	public long maxReceive()
	{
		return 0;
	}

	@Override
	public long maxExtract()
	{
		return maxTransfer;
	}
	
	@Override
	public void outputEnergy(Direction outputDirection)
	{
		ItemStack stack = pItemStackHandler.unprotect().getStackInSlot(0);
		
		if(stack.is(ItemInit.ZPM.get()))
		{
			stack.getCapability(ForgeCapabilities.ENERGY).ifPresent(energy ->
			{
				if(energy instanceof ZeroPointEnergy zpmEnergy)
				{
					BlockEntity blockEntity = level.getBlockEntity(worldPosition.relative(outputDirection));
					
					if(blockEntity == null)
						return;
					
					blockEntity.getCapability(ForgeCapabilities.ENERGY, outputDirection.getOpposite()).ifPresent(blockEntityEnergy ->
					{
						if(blockEntityEnergy instanceof SGJourneyEnergy sgjourneyEnergy)
						{
							long simulatedOutputAmount = zpmEnergy.extractLongEnergy(this.maxExtract(), true);
							long simulatedReceiveAmount = sgjourneyEnergy.receiveLongEnergy(simulatedOutputAmount, true);
							zpmEnergy.extractLongEnergy(simulatedReceiveAmount, false);
							sgjourneyEnergy.receiveLongEnergy(simulatedReceiveAmount, false);
						}
						else
						{
							int simulatedOutputAmount = zpmEnergy.extractEnergy(SGJourneyEnergy.getRegularEnergy(this.maxExtract()), true);
							int simulatedReceiveAmount = blockEntityEnergy.receiveEnergy(simulatedOutputAmount, true);
							
							zpmEnergy.extractLongEnergy(simulatedReceiveAmount, false);
							blockEntityEnergy.receiveEnergy(simulatedReceiveAmount, false);
						}
					});
				}
			});
		}
		
		/*if(ZeroPointModule.hasEnergy(stack))
		{
			BlockEntity blockentity = level.getBlockEntity(worldPosition.relative(outputDirection));
			
			if(blockentity == null)
				return;
			else if(blockentity instanceof EnergyBlockEntity energyBE)
			{
				long simulatedReceiveAmount = energyBE.receiveEnergy(this.maxExtract(), true);
				
				long extractedAmount = this.extractEnergy(simulatedReceiveAmount, true);
				
				this.extractEnergy(extractedAmount, false);
				energyBE.receiveEnergy(extractedAmount, false);
			}
			else
			{
				blockentity.getCapability(ForgeCapabilities.ENERGY, outputDirection.getOpposite()).ifPresent((energyStorage) ->
				{
					int simulatedReceiveAmount = energyStorage.receiveEnergy(SGJourneyEnergy.getRegularEnergy(this.getMaxExtract()), true);
					
					int extractedAmount = SGJourneyEnergy.getRegularEnergy(ZeroPointModule.extractEnergy(stack, simulatedReceiveAmount));
					energyStorage.receiveEnergy(extractedAmount, false);
				});
			}
		}*/
	}
	
	//This should make sure the energy taken by cables is properly subtracted from the ZPM
	/*@Override
	protected void changeEnergy(long difference, boolean simulate)
	{
		ItemStack stack = itemHandler.getStackInSlot(0);
		
		if(!simulate)
			ZeroPointModule.extractEnergy(stack, difference);
		
		super.changeEnergy(difference, simulate);
	}*/
	
	//============================================================================================
	//******************************************Ticking*******************************************
	//============================================================================================
	
	public static void tick(Level level, BlockPos pos, BlockState state, ZPMHubEntity hub)
	{
		if(level.isClientSide)
			return;
		
		hub.outputEnergy(Direction.DOWN);

		//ItemStack stack = hub.itemHandler.getStackInSlot(0);
		//long energy = ZeroPointModule.isNearingEntropy(stack) ? ZeroPointModule.getEnergyInLevel(stack) : ZeroPointModule.getMaxEnergy();
		//hub.setEnergy(energy);
		
		//PacketHandlerInit.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(hub.worldPosition)), new ClientboundNaquadahGeneratorUpdatePacket(hub.worldPosition, hub.getReactionProgress(), hub.getEnergy()));
	}

	//*****************************************Protection*****************************************
	//============================================================================================
	@Override
	public void setProtected(boolean isProtected)
	{
		this.isProtected = isProtected;
	}

	@Override
	public boolean isProtected()
	{
		return isProtected;
	}

	@Override
	public boolean hasPermissions(Player player, boolean sendMessage)
	{
		if(isProtected() && !player.hasPermissions(CommonPermissionConfig.protected_block_permissions.get()))
		{
			if(sendMessage)
				player.displayClientMessage(Component.translatable("block.sgjourney.protected_permissions").withStyle(ChatFormatting.DARK_RED), true);

			return false;
		}

		return true;
	}
}
