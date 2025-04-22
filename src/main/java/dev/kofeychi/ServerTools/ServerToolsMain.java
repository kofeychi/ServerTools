package dev.kofeychi.ServerTools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import dev.kofeychi.ServerTools.common.modules.logger.LogTypes;
import dev.kofeychi.ServerTools.common.modules.logger.PrettyLogger;
import dev.kofeychi.ServerTools.common.util.VectorUtils;
import dev.kofeychi.ServerTools.common.util.shape.AbstractShape;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.joml.Vector2d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;
import static com.mojang.brigadier.arguments.BoolArgumentType.getBool;
import static net.minecraft.command.argument.EntityArgumentType.getPlayers;
import static net.minecraft.command.argument.EntityArgumentType.players;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ServerToolsMain implements ModInitializer {
    public static final Gson GSON = new GsonBuilder().setLenient().create();
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerToolsMain.class);
    public static PrettyLogger PrettyLogger = new PrettyLogger("ServerTools");
    public static MinecraftServer Server;
    public static AbstractShape shape = new AbstractShape(
            List.of(
                    VectorUtils.RotateAround(
                            new Vector2d(),
                            new Vector2d(0,15),
                            360/5*1
                    ),
                    VectorUtils.RotateAround(
                            new Vector2d(),
                            new Vector2d(0,15),
                            360/5*2
                    ),
                    VectorUtils.RotateAround(
                            new Vector2d(),
                            new Vector2d(0,15),
                            360/5*3
                    ),
                    VectorUtils.RotateAround(
                            new Vector2d(),
                            new Vector2d(0,15),
                            360/5*4
                    ),
                    VectorUtils.RotateAround(
                            new Vector2d(),
                            new Vector2d(0,15),
                            360/5*5
                    )
            )
    );
    public static boolean isInside = false;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> Server = server);
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> Server = null);
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            server.getPlayerManager().getPlayerList().forEach(player -> {
                isInside = shape.isInside(new Vector2d(player.getX(), player.getZ()));
            });
        });
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("servertools")
                    .requires(e -> e.hasPermissionLevel(3))
                    .then(
                            literal("debug")
                                    .then(
                                            literal("shape")
                                                    .then(
                                                            literal("addnode")
                                                                    .executes((ctx)->{
                                                                        shape.points.add(new Vector2d(ctx.getSource().getPlayer().getX(), ctx.getSource().getPlayer().getZ()));
                                                                        return 0;
                                                                    })
                                                    ).then(
                                                            literal("delnode")
                                                                    .executes((ctx)->{
                                                                        shape.points.removeLast();
                                                                        return 0;
                                                                    })
                                                    ).then(
                                                            literal("gen")
                                                                    .then(
                                                                            argument("int",IntegerArgumentType.integer(3))
                                                                                    .then(
                                                                                            argument("rad",IntegerArgumentType.integer())
                                                                                    .executes(ctx->{
                                                                                        ArrayList<Vector2d> points = new ArrayList<>();
                                                                                        for (int i = 0; i < IntegerArgumentType.getInteger(ctx,"int"); i++) {
                                                                                            Vector2d center = new Vector2d(ctx.getSource().getPlayer().getX(), ctx.getSource().getPlayer().getZ());
                                                                                            points.add(
                                                                                                    VectorUtils.RotateAround(
                                                                                                            center,
                                                                                                            new Vector2d(0,IntegerArgumentType.getInteger(ctx,"rad")),
                                                                                                            (double) 360 /IntegerArgumentType.getInteger(ctx,"int")*i
                                                                                                    )
                                                                                            );
                                                                                        }
                                                                                        shape = new AbstractShape(points);
                                                                                        return 0;
                                                                                    })
                                                                                    )
                                                                    )
                                                    )
                                    )
                    )
            );
        });
    }
}
