package de.hallotheengineer.fabrictpa.utils;

import com.mojang.brigadier.context.CommandContext;
import java.util.Set;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;

public class TeleportHereRequest extends TeleportRequest {
    public TeleportHereRequest(ServerPlayer source, ServerPlayer target, CommandContext<CommandSourceStack> context) {
        super(source, target, context);
    }

    @Override
    protected void executeTeleport() {
        this.target.teleportTo(target.level(), source.getX(), source.getY(), source.getZ(), Set.of(), source.getYRot(), source.getXRot(), false);
    }
}
