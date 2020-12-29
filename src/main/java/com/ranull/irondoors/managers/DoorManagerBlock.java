package com.ranull.irondoors.managers;

import com.ranull.irondoors.IronDoors;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class DoorManagerBlock implements DoorManager {
    @Override
    public boolean canBuild(IronDoors plugin, Player player, Location location, ItemStack itemStack) {
        itemStack = itemStack == null ? new ItemStack(Material.AIR) : itemStack;

        BlockPlaceEvent blockPlaceEvent = new BlockPlaceEvent(location.getBlock(),
                location.getBlock().getState(), location.getBlock(), itemStack, player, true, EquipmentSlot.HAND);

        plugin.getServer().getPluginManager().callEvent(blockPlaceEvent);

        return blockPlaceEvent.canBuild();
    }

    @Override
    public void toggleDoor(IronDoors plugin, Block block) {
        BlockData blockData = block.getBlockData();

        if (blockData instanceof Openable) {
            Openable openable = (Openable) blockData;

            openable.setOpen(!openable.isOpen());
            block.setBlockData(openable);
            
            if (blockData instanceof Door) {
                block.getWorld().playEffect(block.getLocation(), Effect.IRON_DOOR_TOGGLE, 0);
            } else if (blockData instanceof TrapDoor) {
                block.getWorld().playEffect(block.getLocation(), Effect.IRON_TRAPDOOR_TOGGLE, 0);
            }
        }
    }
}
