package com.ranull.irondoors;

import com.ranull.irondoors.compatibility.Compatibility;
import com.ranull.irondoors.compatibility.CompatibilityBlockData;
import com.ranull.irondoors.listener.PlayerInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

public class IronDoors extends JavaPlugin {
    private Compatibility compatibility;

    @Override
    public void onEnable() {
        compatibility = new CompatibilityBlockData();
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
    }

    public Compatibility getCompatibility() {
        return compatibility;
    }
}
