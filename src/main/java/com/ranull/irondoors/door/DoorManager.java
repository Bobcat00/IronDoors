package com.ranull.irondoors.door;

import com.ranull.irondoors.IronDoors;
import com.ranull.irondoors.animation.Animation;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class DoorManager {
    private IronDoors plugin;

    public DoorManager(IronDoors plugin) {
        this.plugin = plugin;
    }

    public Boolean canBuild(Player player, Location location, ItemStack item) {
        if (item == null) {
            item = new ItemStack(Material.AIR);
        }

        BlockPlaceEvent blockPlaceEvent = new BlockPlaceEvent(location.getBlock(), location.getBlock().getState(), location.getBlock(), item, player, true, EquipmentSlot.HAND);
        plugin.getServer().getPluginManager().callEvent(blockPlaceEvent);

        if (blockPlaceEvent.canBuild()) {
            return true;
        }

        return false;
    }

    public void toggleDoor(Player player, Location location) {
        Block block = location.getBlock();
        BlockData blockData = block.getBlockData();

        if (blockData instanceof Openable) {
            Openable openable = (Openable) blockData;

            if (openable.isOpen()) {
                if (blockData instanceof Door) {
                    block.getWorld().playSound(block.getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 1, 1);
                } else if (blockData instanceof TrapDoor) {
                    block.getWorld().playSound(block.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_CLOSE, 1, 1);
                }

                openable.setOpen(false);
            } else {
                if (blockData instanceof Door) {
                    block.getWorld().playSound(block.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1, 1);
                } else if (blockData instanceof TrapDoor) {
                    block.getWorld().playSound(block.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_OPEN, 1, 1);
                }

                openable.setOpen(true);
            }
            block.setBlockData(openable);

            new Animation(plugin).handAnimation(player);
        }
    }
}
