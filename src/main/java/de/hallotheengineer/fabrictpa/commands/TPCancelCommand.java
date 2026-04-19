package de.hallotheengineer.fabrictpa.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.hallotheengineer.fabrictpa.TeleportHandler;
import de.hallotheengineer.fabrictpa.utils.TeleportRequest;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class TPCancelCommand {
    public static int exec(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        if (!checkRequests(context)) context.getSource().sendSuccess(() -> Component.literal("You have no teleport requests!").withStyle(ChatFormatting.RED), false);
        return 1;
    }
    private static boolean checkRequests(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        for (TeleportRequest request : TeleportHandler.getTpaRequests()) {
            if (request.getSource() == context.getSource().getPlayerOrException()) {
                request.cancel();
                TeleportHandler.removeTPARequest(request);
                context.getSource().sendSuccess(() -> Component.literal("Your teleport request to "+request.getSource().getName().getString()+" has been canceled").withStyle(ChatFormatting.GRAY), false);
                return true;
            }
        }
        return false;
    }

}
