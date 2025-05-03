package de.hallotheengineer.fabrictpa.utils;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class TeleportHereRequest extends TeleportRequest {
    public TeleportHereRequest(ServerPlayerEntity source, ServerPlayerEntity target, CommandContext<ServerCommandSource> context) {
        super(source, target, context);
    }

    @Override
    protected void executeTeleport() {
        this.target.teleport(target.getServerWorld(), source.getX(), source.getY(), source.getZ(), source.getYaw(), source.getPitch());
    }
}
