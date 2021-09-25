package com.ranull.irondoors.compatibility;

import com.ranull.irondoors.IronDoors;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Door;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Openable;
import org.bukkit.material.TrapDoor;

public class CompatibilityMaterialData implements Compatibility {
    @SuppressWarnings("deprecation")
    @Override
    public boolean canInteract(IronDoors plugin, Player player, ItemStack itemStack, Block block, BlockFace blockFace) {
        itemStack = itemStack == null ? new ItemStack(Material.AIR) : itemStack;
        Material newMaterial = null;
        Material material = block.getType();
        BlockState blockState = block.getState();
        MaterialData materialData = blockState.getData().clone();

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
        block.getState().setData(materialData);
        blockState.update(true, false);

        return !playerInteractEvent.isCancelled();
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
            blockState.setData((MaterialData) openable);
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
