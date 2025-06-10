package net.povstalec.sgjourney.common.compatibility.cctweaked;

import dan200.computercraft.api.pocket.PocketUpgradeSerialiser;
import dan200.computercraft.api.turtle.TurtleUpgradeSerialiser;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.povstalec.sgjourney.StargateJourney;
import net.povstalec.sgjourney.common.compatibility.cctweaked.peripherals.PocketTransceiver;
import net.povstalec.sgjourney.common.compatibility.cctweaked.peripherals.TurtleTransceiver;
import net.povstalec.sgjourney.common.init.BlockInit;

public class CCTweakedInit {
    private CCTweakedInit(){}

    public static class PocketUpgradeSerialisers {
        public static DeferredRegister<PocketUpgradeSerialiser<?>> REGISTRY = DeferredRegister.create(PocketUpgradeSerialiser.REGISTRY_ID,StargateJourney.MODID);
        public static RegistryObject<PocketUpgradeSerialiser<PocketTransceiver>> POCKET_TRANSCEIVER = REGISTRY.register(BlockInit.TRANSCEIVER.getId().getPath().toString(), () -> PocketUpgradeSerialiser.simpleWithCustomItem(PocketTransceiver::new));

    }
    public static class TurtleUpgradeSerialisers{
        public static DeferredRegister<TurtleUpgradeSerialiser<?>> REGISTRY = DeferredRegister.create(TurtleUpgradeSerialiser.REGISTRY_ID,StargateJourney.MODID);
        public static RegistryObject<TurtleUpgradeSerialiser<TurtleTransceiver>> TURTLE_TRANSCEIVER = REGISTRY.register(BlockInit.TRANSCEIVER.getId().getPath().toString(), () -> TurtleUpgradeSerialiser.simpleWithCustomItem(TurtleTransceiver::new));
    }

    public static void init(IEventBus eventBus){
        PocketUpgradeSerialisers.REGISTRY.register(eventBus);
        TurtleUpgradeSerialisers.REGISTRY.register(eventBus);
    }
}
