package net.povstalec.sgjourney.common.compatibility.cctweaked;

import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.povstalec.sgjourney.common.config.CommonTransmissionConfig;
import net.povstalec.sgjourney.common.sgjourney.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TransceiverNetwork {
    private TransceiverNetwork() {}

    static final float MAX_DISTANCE = CommonTransmissionConfig.max_transceiver_transmission_distance.get();

    public static double distance2(BlockPos pos, BlockPos targetPos)
    {
        int x = Math.abs(targetPos.getX() - pos.getX());
        int y = Math.abs(targetPos.getY() - pos.getY());
        int z = Math.abs(targetPos.getZ() - pos.getZ());

        return x*x + y*y + z*z;
    }

    // This only tracks the mobile transceivers since we have no easy way to detect them in-world.
    private static final Set<IPortableTransmissionReceiver> receivers = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public static void addReceiver(@NotNull IPortableTransmissionReceiver receiver) {
        Objects.requireNonNull(receiver, "receiver cannot be null");
        synchronized ( receivers ){
            receivers.add(receiver);
        }
    }


    public static void removeReceiver(@NotNull IPortableTransmissionReceiver receiver) {
        Objects.requireNonNull(receiver, "receiver cannot be null");
        synchronized ( receivers ) {
            receivers.remove(receiver);
        }
    }

    public static synchronized void tryTransmit(@NotNull Set<ITransmissionReceiver> transmissionReceivers, ITransciever sender, int transmissionJumps) {
        Objects.requireNonNull(transmissionReceivers, "transmissionReceivers cannot be null");
        Objects.requireNonNull(sender, "sender cannot be null");
        Sets.union(transmissionReceivers, receivers).forEach((receiver1)->{
            if (receiver1.getLevel() == sender.getLevel()) {
                var distanceSq = distance2(sender.getPosition(), receiver1.getPosition());
                if ( distanceSq <= MAX_DISTANCE * MAX_DISTANCE) {
                    receiver1.receiveTransmission(transmissionJumps,sender.getFrequency(),sender.getCurrentCode());
                }
            }
        });
    }

    public static synchronized void tryTransmit(@NotNull Set<ITransmissionReceiver> transmissionReceivers, ITransciever sender) {
        tryTransmit(transmissionReceivers, sender, 0);
    }
}
