package com.ranull.irondoors;

import com.ranull.irondoors.compatibility.Compatibility;
import com.ranull.irondoors.compatibility.CompatibilityBlockData;
import com.ranull.irondoors.compatibility.CompatibilityMaterialData;
import com.ranull.irondoors.listener.PlayerInteractListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IronDoors extends JavaPlugin {
    private String version;
    private Compatibility compatibility;

    @Override
    public void onEnable() {
        version = getServer().getClass().getPackage().getName().split("\\.")[3];
        compatibility = usesBlockData() ? new CompatibilityBlockData() : new CompatibilityMaterialData();

        new Metrics(this, 12870);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
    }

    public Compatibility getCompatibility() {
        return compatibility;
    }

    public void swingMainHand(Player player) {
        if (canSwingHand()) {
            player.swingMainHand();
        } else {
            try {
                Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
                Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
                Method sendPacket = playerConnection.getClass().getMethod("sendPacket", getClass("Packet"));
                Object packetPlayOutAnimation = getClass("PacketPlayOutAnimation")
                        .getConstructor(getClass("Entity"), int.class).newInstance(entityPlayer, 0);

                sendPacket.invoke(playerConnection, packetPlayOutAnimation);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException
                    | ClassNotFoundException | InstantiationException ignored) {
            }
        }
    }

    Class<?> getClass(String clazz) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + getServer().getClass().getPackage().getName()
                .replace(".", ",").split(",")[3] + "." + clazz);
    }

    public boolean hasDoorEffects() {
        return !is_v1_7() && !is_v1_8() && !version.matches("(?i)v1_9_R1");
    }

    public boolean hasSecondHand() {
        return !is_v1_7() && !is_v1_8();
    }

    public boolean usesBlockData() {
        return !is_v1_7() && !is_v1_8() && !is_v1_9() && !is_v1_10() && !is_v1_11() && !is_v1_12();
    }

    public boolean canSwingHand() {
        return !is_v1_7() && !is_v1_8() && !is_v1_9() && !is_v1_10() && !is_v1_11() && !is_v1_12() && !is_v1_13()
                && !is_v1_12() && !is_v1_13() && !is_v1_14() && !is_v1_15();
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean is_v1_7() {
        return version.matches("(?i)v1_7_R1|v1_7_R2|v1_7_R3|v1_7_R4");
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean is_v1_8() {
        return version.matches("(?i)v1_8_R1|v1_8_R2|v1_8_R3");
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean is_v1_9() {
        return version.matches("(?i)v1_9_R1|v1_9_R2");
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean is_v1_10() {
        return version.matches("(?i)v1_10_R1");
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean is_v1_11() {
        return version.matches("(?i)v1_11_R1");
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean is_v1_12() {
        return version.matches("(?i)v1_12_R1");
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean is_v1_13() {
        return version.matches("(?i)v1_13_R1|v1_13_R1");
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean is_v1_14() {
        return version.matches("(?i)v1_14_R1");
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean is_v1_15() {
        return version.matches("(?i)v1_15_R1");
    }
}
