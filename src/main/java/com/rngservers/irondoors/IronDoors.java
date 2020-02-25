package com.rngservers.irondoors;

import com.rngservers.irondoors.door.DoorManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.rngservers.irondoors.events.Events;

public class IronDoors extends JavaPlugin {
	@Override
	public void onEnable() {
		DoorManager doorManager = new DoorManager(this);
		getServer().getPluginManager().registerEvents(new Events(doorManager), this);
	}
}
