package com.rngservers.irondoors.animation;

import net.minecraft.server.v1_14_R1.*;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Animation114 {
	public void handAnimation(Player player) {
		EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
		PlayerConnection connection = entityPlayer.playerConnection;
		PacketPlayOutAnimation armSwing = new PacketPlayOutAnimation(entityPlayer, 0);
		connection.sendPacket(armSwing);
		connection.a(new PacketPlayInArmAnimation(EnumHand.MAIN_HAND));
	}
}
