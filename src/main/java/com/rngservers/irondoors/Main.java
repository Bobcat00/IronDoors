package com.rngservers.irondoors;

import org.bukkit.plugin.java.JavaPlugin;

import com.rngservers.irondoors.events.Events;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new Events(this), this);
	}
}
