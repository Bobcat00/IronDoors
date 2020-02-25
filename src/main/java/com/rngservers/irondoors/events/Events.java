package com.rngservers.irondoors.events;

import com.rngservers.irondoors.door.DoorManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Events implements Listener {
	private DoorManager doorManager;

	public Events(DoorManager doorManager) {
		this.doorManager = doorManager;
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
		if (event.getClickedBlock().getType().equals(Material.IRON_DOOR)
				|| event.getClickedBlock().getType().equals(Material.IRON_TRAPDOOR)) {
			if (doorManager.canBuild(event.getPlayer(), event.getClickedBlock().getLocation(), event.getItem())) {
				doorManager.toggleDoor(event.getPlayer(), event.getClickedBlock().getLocation());
			}
		}
	}
}
