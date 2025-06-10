package net.povstalec.sgjourney.common.sgjourney;

import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static net.povstalec.sgjourney.common.compatibility.cctweaked.TransceiverNetwork.distance2;

public interface ITransciever extends ITranscieverLocation, ITransmissionReceiver, ITransmissionSender {
    // Backward Compatibility
    int getFrequency();
    String getCurrentCode();
    void setFrequency(int frequency);
    void setCurrentCode(String idc);

    int checkShieldingState();


    //============================================================================================
    //******************************************Helpers*******************************************
    //============================================================================================
    default  <T> Set<T> getBlockEntitiesOfType(Class<T> type) {
        int roundedRadius = (int) Math.ceil(transmissionRadius() / 16);
        Set<BlockPos> positions = new HashSet<>();

        for(int x = -roundedRadius; x <= roundedRadius; x++)
        {
            for(int z = -roundedRadius; z <= roundedRadius; z++)
            {
                ChunkAccess chunk = getLevel().getChunk(getPosition().east(16 * x).south(16 * z));
                positions = Sets.union(chunk.getBlockEntitiesPos(),positions);
            }
        }
        return positions.stream()
                .map(pos -> getLevel().getBlockEntity(pos))
                .filter(type::isInstance)
                .filter(entity -> distance2(getPosition(), entity.getBlockPos()) <= transmissionRadius2())
                .map(type::cast)
                .collect(Collectors.toSet());
    }

}
