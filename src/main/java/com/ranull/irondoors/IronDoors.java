package com.ranull.irondoors;

import com.ranull.irondoors.events.Events;
import com.ranull.irondoors.door.DoorManager;
import org.bukkit.plugin.java.JavaPlugin;

public class IronDoors extends JavaPlugin {
	@Override
	public void onEnable() {
		DoorManager doorManager = new DoorManager(this);
		getServer().getPluginManager().registerEvents(new Events(doorManager), this);
	}
}
