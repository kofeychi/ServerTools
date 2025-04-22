package dev.kofeychi.ServerTools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.kofeychi.ServerTools.common.modules.logger.PrettyLogger;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerToolsMain implements ModInitializer {
    public static final Gson GSON = new GsonBuilder().setLenient().create();
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerToolsMain.class);
    public static PrettyLogger PrettyLogger = new PrettyLogger("ServerTools");
    public static MinecraftServer Server;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> Server = server);
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> Server = null);
    }
}
