package net.povstalec.sgjourney.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.povstalec.sgjourney.common.block_entities.dhd.AbstractDHDEntity;
import net.povstalec.sgjourney.common.block_entities.dhd.CrystalDHDEntity;
import net.povstalec.sgjourney.common.block_entities.dhd.PegasusDHDEntity;
import net.povstalec.sgjourney.common.init.BlockInit;
import net.povstalec.sgjourney.common.init.MenuInit;

public class DHDCrystalMenu extends AbstractContainerMenu
{
    private final CrystalDHDEntity blockEntity;
    private final Level level;
    
    public DHDCrystalMenu(int containerId, Inventory inv, FriendlyByteBuf extraData)
    {
        this(containerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public DHDCrystalMenu(int containerId, Inventory inv, BlockEntity entity)
    {
        super(MenuInit.DHD_CRYSTAL.get(), containerId);
        checkContainerSize(inv, 9);
        blockEntity = (CrystalDHDEntity) entity;
        this.level = inv.player.level;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        
        this.blockEntity.getItemHandler().ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 80, 35));
            
            this.addSlot(new SlotItemHandler(handler, 1, 80, 17));
            this.addSlot(new SlotItemHandler(handler, 2, 98, 17));
            this.addSlot(new SlotItemHandler(handler, 3, 98, 35));
            this.addSlot(new SlotItemHandler(handler, 4, 98, 53));
            this.addSlot(new SlotItemHandler(handler, 5, 80, 53));
            this.addSlot(new SlotItemHandler(handler, 6, 62, 53));
            this.addSlot(new SlotItemHandler(handler, 7, 62, 35));
            this.addSlot(new SlotItemHandler(handler, 8, 62, 17));
        });
        
        this.blockEntity.getEnergyItemHandler().ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 134, 27));
            this.addSlot(new SlotItemHandler(handler, 1, 134, 53));
        });
    }
    
    public boolean advancedCrystals()
    {
        return this.blockEntity instanceof PegasusDHDEntity;
    }
    
    public long getEnergy()
    {
        return this.blockEntity.getEnergyStored();
    }
    
    public long getMaxEnergy()
    {
        return this.blockEntity.getEnergyCapacity();
    }
    
    public boolean enableAdvancedProtocols()
    {
        return this.blockEntity.enableAdvancedProtocols();
    }
    
    public long getEnergyTarget()
    {
        return this.blockEntity.getEnergyTarget();
    }
    
    public long maxEnergyDeplete()
    {
        return this.blockEntity.maxEnergyDeplete();
    }
    
    public int getMaxDistance()
    {
        return this.blockEntity.getMaxDistance();
    }
    
    public boolean hasItem(int slot)
    {
        if(slot < 0 || slot > 8)
            return false;
        
        IItemHandler cap = this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
        
        if(cap != null && cap.getStackInSlot(slot) != null)
            return !cap.getStackInSlot(slot).isEmpty();
        
        return false;
    }
	
    @Override
    public boolean stillValid(Player player)
    {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, BlockInit.MILKY_WAY_DHD.get()) ||
        		stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, BlockInit.PEGASUS_DHD.get()) ||
        		stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, BlockInit.CLASSIC_DHD.get());
    }

    private void addPlayerInventory(Inventory playerInventory)
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int l = 0; l < 9; ++l)
            {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory)
    {
        for (int i = 0; i < 9; ++i)
        {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
    
	// CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 10;  // must match TileEntityInventoryBasic.NUMBER_OF_SLOTS

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) 
    {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) 
        {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) 
            {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } 
        else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) 
        {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) 
            {
                return ItemStack.EMPTY;
            }
        } else 
        {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) 
        {
            sourceSlot.set(ItemStack.EMPTY);
        } else 
        {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
	
}
