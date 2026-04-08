package de.hallotheengineer.fabrictpa.utils;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Set;

public class TeleportHereRequest extends TeleportRequest {
    public TeleportHereRequest(ServerPlayerEntity source, ServerPlayerEntity target, CommandContext<ServerCommandSource> context) {
        super(source, target, context);
    }

    @Override
    protected void executeTeleport() {
        this.target.teleport(target.getEntityWorld(), source.getX(), source.getY(), source.getZ(), Set.of(), source.getYaw(), source.getPitch(), false);
    }
}
