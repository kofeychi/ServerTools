package dev.kofeychi.ServerTools;

import dev.kofeychi.ServerTools.common.modules.scheduler.Scheduler;
import dev.kofeychi.ServerTools.common.render.GuiTexture;
import dev.kofeychi.ServerTools.common.render.WindowFrameRenderer;
import dev.kofeychi.ServerTools.common.util.VectorUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import org.joml.Vector2d;

public class ServerToolsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) -> {
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Scheduler.CLIENT.tick();
        });
    }
}
