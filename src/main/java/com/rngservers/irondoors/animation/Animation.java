package com.rngservers.irondoors.animation;

import com.rngservers.irondoors.Main;
import org.bukkit.entity.Player;

public class Animation {
    private Main plugin;

    public Animation(Main plugin) {
        this.plugin = plugin;
    }

    public void handAnimation(Player player) {
        if (plugin.getServer().getVersion().contains("1.13")) {
            new Animation113().handAnimation(player);
        } else if (plugin.getServer().getVersion().contains("1.14")) {
            new Animation114().handAnimation(player);
        } else if (plugin.getServer().getVersion().contains("1.15")) {
            new Animation115().handAnimation(player);
        }
    }
}