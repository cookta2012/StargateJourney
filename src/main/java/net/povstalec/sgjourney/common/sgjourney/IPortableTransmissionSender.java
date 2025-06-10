package net.povstalec.sgjourney.common.sgjourney;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.ChunkAccess;

import java.util.Set;

public interface IPortableTransmissionSender extends ITransmissionSender, ITransciever {

    default void sendTransmission()
    {
        Level level = getLevel();
        int roundedRadius = (int) Math.ceil(transmissionRadius() / 16);

        for(int x = -roundedRadius; x <= roundedRadius; x++)
        {
            for(int z = -roundedRadius; z <= roundedRadius; z++)
            {
                ChunkAccess chunk = getLevel().getChunk(getPosition().east(16 * x).south(16 * z));
                Set<BlockPos> positions = chunk.getBlockEntitiesPos();

                positions.stream().forEach(pos ->
                {
                    BlockEntity blockEntity = level.getBlockEntity(pos);

                    if(blockEntity instanceof ITransmissionReceiver receiver && blockEntity != this)
                        receiver.receiveTransmission(0, getFrequency(), getCurrentCode());
                });
            }

        }
    }
}
