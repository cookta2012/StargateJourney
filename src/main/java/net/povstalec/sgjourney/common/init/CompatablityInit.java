package net.povstalec.sgjourney.common.init;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.povstalec.sgjourney.common.compatibility.cctweaked.CCTweakedInit;

import javax.annotation.Nullable;

public class CompatablityInit {
    private CompatablityInit(){}
    public static void register(IEventBus eventBus) {
        if (ModList.get().isLoaded("computercraft")) {
            CCTweakedInit.init(eventBus);
        }
    }
}
