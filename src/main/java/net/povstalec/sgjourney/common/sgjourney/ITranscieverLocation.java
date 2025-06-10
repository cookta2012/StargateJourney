package net.povstalec.sgjourney.common.sgjourney;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.povstalec.sgjourney.common.config.CommonTransmissionConfig;

public interface ITranscieverLocation {

    default float transmissionRadius()
    {
        return CommonTransmissionConfig.max_transceiver_transmission_distance.get();
    }
    default float transmissionRadius2()
    {
        return transmissionRadius() * transmissionRadius();
    }

    default double distance2(BlockPos pos, BlockPos targetPos)
    {
        int x = Math.abs(targetPos.getX() - pos.getX());
        int y = Math.abs(targetPos.getY() - pos.getY());
        int z = Math.abs(targetPos.getZ() - pos.getZ());

        return x*x + y*y + z*z;
    }

    Level getLevel();
    BlockPos getPosition();
}
