package com.ranull.irondoors.compatibility;

import com.ranull.irondoors.IronDoors;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
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

public class CompatibilityBlockData implements Compatibility {

    Random random = new Random();

    @Override
    public boolean canInteract(IronDoors plugin, Player player, ItemStack itemStack, Block block, BlockFace blockFace) {
        itemStack = itemStack == null ? new ItemStack(Material.AIR) : itemStack;
        Material newMaterial = null;
        Material material = block.getType();
        BlockData blockData = block.getBlockData().clone();

        if (block.getType().toString().equals("IRON_DOOR")) {
            newMaterial = Material.getMaterial("OAK_DOOR");
        } else if (block.getType().toString().equals("IRON_TRAPDOOR")) {
            newMaterial = Material.getMaterial("OAK_TRAPDOOR");
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

            boolean isOpen = openable.isOpen();
            openable.setOpen(!isOpen);
            block.setBlockData(openable);

            Sound sound = null;
            if (blockData instanceof Door) {
                sound = isOpen ? Sound.BLOCK_IRON_DOOR_CLOSE : Sound.BLOCK_IRON_DOOR_OPEN;
            } else if (blockData instanceof TrapDoor) {
                sound = isOpen ? Sound.BLOCK_IRON_TRAPDOOR_CLOSE : Sound.BLOCK_IRON_TRAPDOOR_OPEN;
            }
            if (sound != null) {
                block.getWorld().playSound(block.getLocation(), sound, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F);
            }
        }
    }
}
