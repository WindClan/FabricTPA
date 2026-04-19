package de.hallotheengineer.fabrictpa;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.hallotheengineer.fabrictpa.commands.*;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class Commands {
    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registryAccess, net.minecraft.commands.Commands.CommandSelection environment) {
        LiteralArgumentBuilder<CommandSourceStack> tpaCommand = literal("tpa")
                .then(argument("player", EntityArgument.player())
                        .executes(TPACommand::exec));
        LiteralArgumentBuilder<CommandSourceStack> tpCancelCommand = literal("tpcancel")
                .executes(TPCancelCommand::exec);
        LiteralArgumentBuilder<CommandSourceStack> tpAcceptCommand = literal("tpaccept")
                .then(argument("ownerUUID", string())
                        .executes(ctx -> TPAcceptCommand.exec(ctx,getString(ctx,"ownerUUID"))))
                .executes(ctx -> TPAcceptCommand.exec(ctx,null));
        LiteralArgumentBuilder<CommandSourceStack> tpDenyCommand = literal("tpdeny")
                .then(argument("ownerUUID", string())
                        .executes(ctx -> TPDenyCommand.exec(ctx,getString(ctx,"ownerUUID"))))
                .executes(ctx -> TPDenyCommand.exec(ctx,null));
        LiteralArgumentBuilder<CommandSourceStack> tpaHereCommand = literal("tpahere")
                .then(argument("player", EntityArgument.player())
                        .executes(TPAHereCommand::exec));



        dispatcher.register(tpaCommand);
        dispatcher.register(tpCancelCommand);
        dispatcher.register(tpAcceptCommand);
        dispatcher.register(tpDenyCommand);
        dispatcher.register(tpaHereCommand);
    }

}
