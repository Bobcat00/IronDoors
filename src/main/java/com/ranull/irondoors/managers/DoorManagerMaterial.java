package com.ranull.irondoors.managers;

import com.ranull.irondoors.IronDoors;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Door;
import org.bukkit.material.Openable;
import org.bukkit.material.TrapDoor;

public class DoorManagerMaterial implements DoorManager {
    @SuppressWarnings("deprecation")
    @Override
    public boolean canBuild(IronDoors plugin, Player player, Location location, ItemStack itemStack) {
        itemStack = itemStack == null ? new ItemStack(Material.AIR) : itemStack;

        BlockPlaceEvent blockPlaceEvent = new BlockPlaceEvent(location.getBlock(), location.getBlock().getState(), location.getBlock(), itemStack, player, true);

        plugin.getServer().getPluginManager().callEvent(blockPlaceEvent);

        return blockPlaceEvent.canBuild();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void toggleDoor(IronDoors plugin, Block block) {
        if (block.getState().getData() instanceof Door && ((Door) block.getState().getData()).isTopHalf()) {
            block = block.getRelative(BlockFace.DOWN);
        }

        if (block.getState().getData() instanceof Openable) {
            BlockState blockState = block.getState();
            Openable openable = (Openable) blockState.getData();

            openable.setOpen(!openable.isOpen());
            blockState.setData((org.bukkit.material.MaterialData) openable);
            blockState.update(true, true);

            if (plugin.hasDoorEffects()) {
                if (openable instanceof Door) {
                    block.getWorld().playEffect(block.getLocation(), Effect.IRON_DOOR_TOGGLE, 0);
                } else if (openable instanceof TrapDoor) {
                    block.getWorld().playEffect(block.getLocation(), Effect.IRON_TRAPDOOR_TOGGLE, 0);
                }
            } else {
                block.getWorld().playEffect(block.getLocation(), Effect.DOOR_TOGGLE, 0);
            }
        }
    }
}
