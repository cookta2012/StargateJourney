package net.povstalec.sgjourney.common.compatibility.cctweaked.peripherals;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.pocket.AbstractPocketUpgrade;
import dan200.computercraft.api.pocket.IPocketAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.povstalec.sgjourney.common.handlers.TransceiverState;
import org.jetbrains.annotations.Nullable;

public class PocketTransceiver extends AbstractPocketUpgrade{
    static private BaseTransceiverPeripheral baseTransceiverPeripheral;
    static private TransceiverState state;

    public PocketTransceiver(ResourceLocation id, ItemStack stack) {
        super(id, stack);
    }

    /**
     * @param stack The stack to check. This is guaranteed to be non-empty and have the same item as
     *              {@link #getCraftingItem()}.
     * @return
     */
    @Override
    public boolean isItemSuitable(ItemStack stack) {
        //if(stack.getItem().asItem().toString().equals("sgjourney:transceiver"))
        return stack.getItem().asItem().toString().equals("sgjourney:transceiver"); //super.isItemSuitable(stack);
    }

    /**
     * @param access The access object for the pocket item stack.
     * @return
     */
    @Override
    public @Nullable IPeripheral createPeripheral(IPocketAccess access) {
        state = new TransceiverState();
        return new BaseTransceiverPeripheral(access, state) {

            @Override
            public Level getLevel() {
                if(access.getEntity() instanceof Player player)
                    return access.getEntity().getLevel();
                return null;
            }

            @Override
            public BlockPos getPosition() {
                if(access.getEntity() instanceof Player player)
                    return access.getEntity().getOnPos();
                return null;
            }
        };
    }

    /**
     * @param access     The access object for the pocket item stack.
     * @param peripheral The peripheral for this upgrade.
     */
    @Override
    public void update(IPocketAccess access, @Nullable IPeripheral peripheral) {
        super.update(access, peripheral);
    }

    /**
     * @param world      The world the computer is in.
     * @param access     The access object for the pocket item stack.
     * @param peripheral The peripheral for this upgrade.
     * @return
     */
    @Override
    public boolean onRightClick(Level world, IPocketAccess access, @Nullable IPeripheral peripheral) {
        return super.onRightClick(world, access, peripheral);
    }
}
