package net.povstalec.sgjourney.common.compatibility.cctweaked.peripherals;

import dan200.computercraft.api.turtle.AbstractTurtleUpgrade;
import dan200.computercraft.api.turtle.TurtleUpgradeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class TurtleTransceiver extends AbstractTurtleUpgrade {
    public TurtleTransceiver(ResourceLocation id, ItemStack itemStack) {
        super(id,TurtleUpgradeType.PERIPHERAL,itemStack);
    }
}
