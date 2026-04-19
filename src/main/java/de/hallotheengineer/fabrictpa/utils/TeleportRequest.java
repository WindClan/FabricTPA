package de.hallotheengineer.fabrictpa.utils;

import com.mojang.brigadier.context.CommandContext;
import java.util.Set;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;

public class TeleportRequest {
    protected final ServerPlayer source;
    protected final ServerPlayer target;
    private final CommandContext<CommandSourceStack> context;
    public TeleportRequest(ServerPlayer source, ServerPlayer target,  CommandContext<CommandSourceStack> context) {
        this.source = source;
        this.target = target;
        this.context = context;
    }
    public void run() {
        if (!source.hasDisconnected() && !target.hasDisconnected()) {
            executeTeleport();
        }
    }
    protected void executeTeleport() {
        source.teleportTo(target.level(), target.getX(), target.getY(), target.getZ(), Set.of(), target.getYRot(), target.getXRot(),false);
    }
    public void cancel() {}

    public ServerPlayer getSource() {
        return source;
    }
    public ServerPlayer getOwner() {return context.getSource().getPlayer();}
    public ServerPlayer getTarget() {
        return target;
    }
    private TeleportRequest getInstance() {
        return this;
    }
}
