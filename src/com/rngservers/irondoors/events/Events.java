package com.rngservers.irondoors.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Events implements Listener {
	@EventHandler
	public void onIronDoor(PlayerInteractEvent event) {
		if (!event.getPlayer().hasPermission("irondoors.use")) {
			return;
		}
		if (event.getClickedBlock() == null) {
			return;
		}
		if (!event.getHand().equals(EquipmentSlot.HAND)) {
			return;
		}
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		if (event.getClickedBlock().getType().equals(Material.IRON_DOOR)
				|| event.getClickedBlock().getType().equals(Material.IRON_TRAPDOOR)) {
			Block block = event.getClickedBlock();
			BlockData blockData = block.getBlockData();
			if (blockData instanceof Openable) {
				Openable door = (Openable) blockData;
				if (door.isOpen()) {
					if (blockData instanceof Door) {
						block.getWorld().playSound(block.getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 1, 1);
					} else if (blockData instanceof TrapDoor) {
						block.getWorld().playSound(block.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_CLOSE, 1, 1);
					}
					door.setOpen(false);
				} else {
					if (blockData instanceof Door) {
						block.getWorld().playSound(block.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1, 1);
					} else if (blockData instanceof TrapDoor) {
						block.getWorld().playSound(block.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_OPEN, 1, 1);
					}
					door.setOpen(true);
				}
				block.setBlockData(door);
			}
		}
	}
}
