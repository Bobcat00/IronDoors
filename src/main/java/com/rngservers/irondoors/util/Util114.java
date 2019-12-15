package com.rngservers.irondoors.util;

import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_14_R1.EntityPlayer;
import net.minecraft.server.v1_14_R1.EnumHand;
import net.minecraft.server.v1_14_R1.PacketPlayInArmAnimation;
import net.minecraft.server.v1_14_R1.PacketPlayOutAnimation;
import net.minecraft.server.v1_14_R1.PlayerConnection;

public class Util114 {
	public void handAnimation(Player player) {
		EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
		PlayerConnection connection = entityPlayer.playerConnection;
		PacketPlayOutAnimation armSwing = new PacketPlayOutAnimation(entityPlayer, 0);
		connection.sendPacket(armSwing);
		connection.a(new PacketPlayInArmAnimation(EnumHand.MAIN_HAND));
	}
}
