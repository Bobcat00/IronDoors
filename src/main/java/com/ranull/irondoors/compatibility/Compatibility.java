package com.ranull.irondoors.compatibility;

import com.ranull.irondoors.IronDoors;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Compatibility {
    boolean canInteract(IronDoors plugin, Player player, ItemStack itemStack, Block block, BlockFace blockFace);

    void toggleDoor(IronDoors plugin, Block block);
}
