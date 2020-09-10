package com.ranull.irondoors.listeners;

import com.ranull.irondoors.IronDoors;
import com.ranull.irondoors.managers.DoorManager;
import com.ranull.irondoors.nms.NMS;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractListener implements Listener {
	private IronDoors plugin;
	private DoorManager doorManager;
	private NMS nms;

	public PlayerInteractListener(IronDoors plugin, DoorManager doorManager, NMS nms) {
		this.plugin = plugin;
		this.doorManager = doorManager;
		this.nms = nms;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (plugin.hasSecondHand() && event.getHand() != EquipmentSlot.HAND) {
			return;
		}

		Player player = event.getPlayer();
		Block block = event.getClickedBlock();

		if (player.hasPermission("irondoors.use")
				&& !player.isSneaking()
				&& event.getAction() == Action.RIGHT_CLICK_BLOCK
				&& block != null
				&& (block.getType().toString().equals("IRON_DOOR_BLOCK")
				|| block.getType().toString().equals("IRON_TRAPDOOR"))
				&& doorManager.canBuild(plugin, player, block.getLocation(), event.getPlayer().getItemInHand())) {
			doorManager.toggleDoor(plugin, block);

			if (nms != null) {
				nms.mainHandAnimation(player);
			}

			if (event.isBlockInHand()) {
				event.setCancelled(true);
			}
		}
	}
}
