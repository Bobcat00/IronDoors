package com.ranull.irondoors.managers;

import com.ranull.irondoors.IronDoors;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface DoorManager {
    boolean canBuild(IronDoors plugin, Player player, Location location, ItemStack itemStack);

    void toggleDoor(IronDoors plugin, Block block);
}
