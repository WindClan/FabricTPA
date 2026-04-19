package de.hallotheengineer.fabrictpa;

import com.mojang.brigadier.context.CommandContext;
import de.hallotheengineer.fabrictpa.utils.TeleportHereRequest;
import de.hallotheengineer.fabrictpa.utils.TeleportRequest;
import java.util.*;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;

public class TeleportHandler {
    private static final List<TeleportRequest> tpaRequests = new ArrayList<>();
    public static void newAskReqest(ServerPlayer source, ServerPlayer target, CommandContext<CommandSourceStack> context) {
        TeleportRequest request = new TeleportRequest(source, target, context);
        tpaRequests.add(request);
        target.sendSystemMessage(Component.literal(source.getName().getString()+" wants to teleport to you\n")
                .withStyle(ChatFormatting.GREEN)
                .append(Component.literal("[Accept]")
                        .setStyle(Style.EMPTY.withClickEvent(new ClickEvent.RunCommand("/tpaccept "+ source.getStringUUID())))
                        .withStyle(ChatFormatting.GREEN))
                .append("     ")
                .append(Component.literal("[Deny]")
                        .setStyle(Style.EMPTY.withClickEvent(new ClickEvent.RunCommand("/tpdeny "+ source.getStringUUID())))
                .withStyle(ChatFormatting.RED)));
    }
    public static void newAskHereReqest(ServerPlayer source, ServerPlayer target, CommandContext<CommandSourceStack> context) {
        TeleportRequest request = new TeleportHereRequest(source, target, context);
        tpaRequests.add(request);
        target.sendSystemMessage(Component.literal(source.getName().getString()+" wants you to teleport to them\n")
                .withStyle(ChatFormatting.GREEN)
                .append(Component.literal("[Accept]")
                        .setStyle(Style.EMPTY.withClickEvent(new ClickEvent.RunCommand("/tpaccept " + source.getStringUUID())))
                        .withStyle(ChatFormatting.GREEN))
                .append("     ")
                .append(Component.literal("[Deny]")
                        .setStyle(Style.EMPTY.withClickEvent(new ClickEvent.RunCommand("/tpdeny " + source.getStringUUID())))
                        .withStyle(ChatFormatting.RED)));
    }

    public static List<TeleportRequest> getTpaRequests() {
        return tpaRequests;
    }
    public static void removeTPARequest(TeleportRequest request) {
        tpaRequests.remove(request);
    }
}
