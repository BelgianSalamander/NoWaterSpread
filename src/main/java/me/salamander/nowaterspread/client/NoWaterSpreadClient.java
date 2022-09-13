package me.salamander.nowaterspread.client;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.network.chat.TextComponent;

import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.*;
import static com.mojang.brigadier.arguments.BoolArgumentType.*;

public class NoWaterSpreadClient implements ClientModInitializer {
    public static boolean FLUID_BLOCKS = false;

    @Override
    public void onInitializeClient() {
        DISPATCHER.register(
                literal("selectablefluids")
                        .executes(context -> {
                            context.getSource().sendFeedback(
                                    new TextComponent("Selectable fluids is currently " + (FLUID_BLOCKS ? "enabled" : "disabled"))
                            );

                            return 1;
                        })
                        .then(
                                literal("toggle")
                                        .executes(
                                                context -> this.setFluidBlocks(context, !FLUID_BLOCKS)
                                        )
                        )
                        .then(
                                literal("enable")
                                        .executes(
                                                context -> this.setFluidBlocks(context, true)
                                        )
                        )
                        .then(
                                literal("disable")
                                        .executes(
                                                context -> this.setFluidBlocks(context, false)
                                        )
                        )
                        .then(
                                literal("set")
                                        .then(
                                                argument("value", bool())
                                                        .executes(
                                                                context -> this.setFluidBlocks(context, getBool(context, "value"))
                                                        )
                                        )
                        )
        );
    }

    private int setFluidBlocks(CommandContext<FabricClientCommandSource> context, boolean value){
        FLUID_BLOCKS = value;
        context.getSource().sendFeedback(
                new TextComponent("Selectable fluids is now " + (FLUID_BLOCKS ? "enabled" : "disabled"))
        );

        return 1;
    }
}
