package dev.kofeychi.manageable;

import dev.kofeychi.manageable.common.modules.scheduler.Scheduler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ManageableClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) -> {
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Scheduler.CLIENT.tick();
        });
    }
}
