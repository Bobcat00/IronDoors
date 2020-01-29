package com.rngservers.irondoors.events;

import com.rngservers.irondoors.animation.Animation;
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
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.rngservers.irondoors.Main;

public class Events implements Listener {
	private Main plugin;

	public Events(Main plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onIronDoor(PlayerInteractEvent event) {
		if (event.isCancelled() ||
				!event.getPlayer().hasPermission("irondoors.use") ||
				event.getClickedBlock() == null ||
				event.getHand() == null ||
				!event.getHand().equals(EquipmentSlot.HAND) ||
				!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		BlockPlaceEvent placeEvent = new BlockPlaceEvent(event.getClickedBlock(), event.getClickedBlock().getState(), event.getClickedBlock(), event.getItem(), event.getPlayer(), true, EquipmentSlot.HAND);
		plugin.getServer().getPluginManager().callEvent(placeEvent);
		if (!placeEvent.canBuild()) {
			return;
		}
		if (event.getClickedBlock().getType().equals(Material.IRON_DOOR)
				|| event.getClickedBlock().getType().equals(Material.IRON_TRAPDOOR)) {
			Block block1 = event.getClickedBlock();
			BlockData blockData = block1.getBlockData();
			if (blockData instanceof Openable) {
				Openable door = (Openable) blockData;
				if (door.isOpen()) {
					if (blockData instanceof Door) {
						block1.getWorld().playSound(block1.getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 1, 1);
					} else if (blockData instanceof TrapDoor) {
						block1.getWorld().playSound(block1.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_CLOSE, 1, 1);
					}
					door.setOpen(false);
				} else {
					if (blockData instanceof Door) {
						block1.getWorld().playSound(block1.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1, 1);
					} else if (blockData instanceof TrapDoor) {
						block1.getWorld().playSound(block1.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_OPEN, 1, 1);
					}
					door.setOpen(true);
				}
				block1.setBlockData(door);
				new Animation(plugin).handAnimation(event.getPlayer());
			}
		}
	}
}
