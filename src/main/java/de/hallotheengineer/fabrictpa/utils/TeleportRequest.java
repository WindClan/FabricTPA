package de.hallotheengineer.fabrictpa.utils;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class TeleportRequest {
    protected final ServerPlayerEntity source;
    protected final ServerPlayerEntity target;
    private final CommandContext<ServerCommandSource> context;
    public TeleportRequest(ServerPlayerEntity source, ServerPlayerEntity target,  CommandContext<ServerCommandSource> context) {
        this.source = source;
        this.target = target;
        this.context = context;
    }
    public void run() {
        if (!source.isDisconnected() && !target.isDisconnected()) {
            executeTeleport();
        }
    }
    protected void executeTeleport() {
        source.teleport(target.getServerWorld(), target.getX(), target.getY(), target.getZ(), target.getYaw(), target.getPitch());
    }
    public void cancel() {}

    public ServerPlayerEntity getSource() {
        return source;
    }
    public ServerPlayerEntity getOwner() {return context.getSource().getPlayer();}
    public ServerPlayerEntity getTarget() {
        return target;
    }
    private TeleportRequest getInstance() {
        return this;
    }
}
