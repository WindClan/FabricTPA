package de.hallotheengineer.fabrictpa.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.hallotheengineer.fabrictpa.TeleportHandler;
import de.hallotheengineer.fabrictpa.utils.TeleportRequest;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class TPDenyCommand {
    public static int exec(CommandContext<CommandSourceStack> context,String ownerUUID) throws CommandSyntaxException {
        if (!checkRequests(context,ownerUUID)) context.getSource().sendSuccess(() -> Component.literal("You have no teleport requests!").withStyle(ChatFormatting.RED), false);
        return 1;
    }
    private static boolean checkRequests(CommandContext<CommandSourceStack> context,String ownerUUID) throws CommandSyntaxException {
        for (TeleportRequest request : TeleportHandler.getTpaRequests()) {
            if (request.getTarget() == context.getSource().getPlayerOrException() && (ownerUUID == null || ownerUUID.equals(request.getOwner().getStringUUID()))) {
                request.cancel();
                TeleportHandler.removeTPARequest(request);
                request.getSource().sendSystemMessage(Component.literal("Your request was denied!").withStyle(ChatFormatting.RED));
                request.getTarget().sendSystemMessage(Component.literal("Request denied.").withStyle(ChatFormatting.GRAY));
                return true;
            }
        }
        return false;
    }
}
