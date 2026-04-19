package de.hallotheengineer.fabrictpa.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.hallotheengineer.fabrictpa.TeleportHandler;
import de.hallotheengineer.fabrictpa.utils.TeleportRequest;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class TPACommand {
    public static int exec(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer target = EntityArgument.getPlayer(context, "player");
        CommandSourceStack source = context.getSource();
        ServerPlayer player = source.getPlayerOrException();

        if (target == player) {
            source.sendSuccess(() -> Component.literal("You can't teleport to yourself").withStyle(ChatFormatting.RED), false);
            return -1;
        }
        for (TeleportRequest request : TeleportHandler.getTpaRequests()) {
            if (request.getSource() == player) {
                source.sendSuccess(() -> Component.literal("You already have ongoing requests").withStyle(ChatFormatting.RED), false);
                return -1;
            }
        }
        TeleportHandler.newAskReqest(player, target, context);
        context.getSource().sendSuccess(() -> Component.literal("Teleport request sent to "+target.getName().getString()).withStyle(ChatFormatting.GRAY), false);
        return 1;
    }
}
