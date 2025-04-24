package dev.kofeychi.manageable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.kofeychi.manageable.common.modules.logger.PrettyLogger;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManageableMain implements ModInitializer {
    public static final Gson GSON = new GsonBuilder().setLenient().create();
    public static final Logger LOGGER = LoggerFactory.getLogger(ManageableMain.class);
    public static PrettyLogger PrettyLogger = new PrettyLogger("manageable");
    public static MinecraftServer Server;
    public static boolean renderInvisibleButtons = true;

    public static Identifier of(String path){
        return Identifier.of("manageable", path);
    }

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> Server = server);
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> Server = null);
    }
}
