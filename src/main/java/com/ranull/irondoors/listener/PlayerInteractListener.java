package com.ranull.irondoors.listener;

import com.ranull.irondoors.IronDoors;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractListener implements Listener {
    private final IronDoors plugin;

    public PlayerInteractListener(IronDoors plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block != null
                && !player.isSneaking()
                && event.getAction() == Action.RIGHT_CLICK_BLOCK
                && ((block.getType().toString().equals("IRON_DOOR_BLOCK")
                && player.hasPermission("irondoors.irondoor"))
                || (block.getType().toString().equals("IRON_TRAPDOOR")
                && player.hasPermission("irondoors.irontrapdoor")))) {
            if ((!plugin.hasSecondHand() || event.getHand() == EquipmentSlot.HAND)
                    && (plugin.is_v1_7() || plugin.getCompatibility().canInteract(plugin, player,
                    event.getPlayer().getItemInHand(), block, event.getBlockFace()))) {
                plugin.getCompatibility().toggleDoor(plugin, block);
                plugin.swingMainHand(player);

                if (event.isBlockInHand()) {
                    event.setCancelled(true);
                }
            }
        }
    }
}