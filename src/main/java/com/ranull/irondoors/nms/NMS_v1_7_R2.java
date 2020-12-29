package com.ranull.irondoors.nms;

import net.minecraft.server.v1_7_R2.EntityPlayer;
import net.minecraft.server.v1_7_R2.PacketPlayInArmAnimation;
import net.minecraft.server.v1_7_R2.PacketPlayOutAnimation;
import net.minecraft.server.v1_7_R2.PlayerConnection;
import org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMS_v1_7_R2 implements NMS {
    @Override
    public void mainHandAnimation(Player player) {
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        PlayerConnection playerConnection = entityPlayer.playerConnection;
        PacketPlayOutAnimation packetPlayOutAnimation = new PacketPlayOutAnimation(entityPlayer, 0);

        playerConnection.sendPacket(packetPlayOutAnimation);
        playerConnection.a(new PacketPlayInArmAnimation());
    }
}
