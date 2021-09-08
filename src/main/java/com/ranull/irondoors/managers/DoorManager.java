package com.ranull.irondoors.managers;

import com.ranull.irondoors.IronDoors;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface DoorManager {
    boolean canInteract(IronDoors plugin, Player player, ItemStack itemStack, Block block, BlockFace blockFace);

    void toggleDoor(IronDoors plugin, Block block);
}
