package de.hallotheengineer.fabrictpa.utils;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TeleportTarget;

import java.util.Set;

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
        source.teleport(target.getEntityWorld(), target.getX(), target.getY(), target.getZ(), Set.of(), target.getYaw(), target.getPitch(),false);
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
