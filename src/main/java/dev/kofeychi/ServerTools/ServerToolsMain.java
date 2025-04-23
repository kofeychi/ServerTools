package dev.kofeychi.ServerTools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.kofeychi.ServerTools.common.modules.logger.PrettyLogger;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

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
