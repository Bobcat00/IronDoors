package com.ranull.irondoors.managers;

import com.ranull.irondoors.IronDoors;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class DoorManagerBlock implements DoorManager {
    @Override
    public boolean canInteract(IronDoors plugin, Player player, ItemStack itemStack, Block block, BlockFace blockFace) {
        itemStack = itemStack == null ? new ItemStack(Material.AIR) : itemStack;
        Material newMaterial = null;
        Material material = block.getType();
        BlockData blockData = block.getBlockData().clone();

        if (block.getType().toString().equals("IRON_DOOR_BLOCK")) {
            newMaterial = Material.getMaterial("WOODEN_DOOR");
        } else if (block.getType().toString().equals("IRON_TRAPDOOR")) {
            newMaterial = Material.getMaterial("TRAP_DOOR");
        }

        if (newMaterial != null) {
            block.setType(newMaterial, false);
        }

        PlayerInteractEvent playerInteractEvent = new PlayerInteractEvent(player, Action.RIGHT_CLICK_BLOCK, itemStack, block, blockFace);

        plugin.getServer().getPluginManager().callEvent(playerInteractEvent);

        block.setType(material, false);
        block.setBlockData(blockData, false);

        return playerInteractEvent.useInteractedBlock() != Event.Result.DENY;
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
