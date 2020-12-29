package com.ranull.irondoors;

import com.ranull.irondoors.listeners.PlayerInteractListener;
import com.ranull.irondoors.managers.DoorManager;
import com.ranull.irondoors.managers.DoorManagerBlock;
import com.ranull.irondoors.managers.DoorManagerMaterial;
import com.ranull.irondoors.nms.*;
import org.bukkit.plugin.java.JavaPlugin;

public class IronDoors extends JavaPlugin {
    private String version;
    private NMS nms;
    private DoorManager doorManager;

    @Override
    public void onEnable() {
        if (!setupNMS()) {
            getLogger().severe("Version not fully supported! Continuing anyway...");
        }

        setupDoorManager();
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this, doorManager, nms), this);
    }

    private boolean setupNMS() {
        version = getServer().getClass().getPackage().getName().split("\\.")[3];

        try {
            if (version.equals("v1_7_R1")) {
                nms = new NMS_v1_7_R1();
            } else if (version.equals("v1_7_R2")) {
                nms = new NMS_v1_7_R2();
            } else if (version.equals("v1_7_R3")) {
                nms = new NMS_v1_7_R3();
            } else if (version.equals("v1_7_R4")) {
                nms = new NMS_v1_7_R4();
            } else if (version.equals("v1_8_R1")) {
                nms = new NMS_v1_8_R1();
            } else if (version.equals("v1_8_R2")) {
                nms = new NMS_v1_8_R2();
            } else if (version.equals("v1_8_R3")) {
                nms = new NMS_v1_8_R3();
            } else if (version.equals("v1_9_R1")) {
                nms = new NMS_v1_9_R1();
            } else if (version.equals("v1_9_R2")) {
                nms = new NMS_v1_9_R2();
            } else if (version.equals("v1_10_R1")) {
                nms = new NMS_v1_10_R1();
            } else if (version.equals("v1_11_R1")) {
                nms = new NMS_v1_11_R1();
            } else if (version.equals("v1_12_R1")) {
                nms = new NMS_v1_12_R1();
            } else if (version.equals("v1_13_R1")) {
                nms = new NMS_v1_13_R1();
            } else if (version.equals("v1_13_R2")) {
                nms = new NMS_v1_13_R2();
            } else if (version.equals("v1_14_R1")) {
                nms = new NMS_v1_14_R1();
            } else if (version.equals("v1_15_R1")) {
                nms = new NMS_v1_15_R1();
            } else if (version.equals("v1_16_R1")) {
                nms = new NMS_v1_16_R1();
            } else if (version.equals("v1_16_R2")) {
                nms = new NMS_v1_16_R2();
            } else if (version.equals("v1_16_R3")) {
                nms = new NMS_v1_16_R3();
            }

            return nms != null;
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return false;
        }
    }

    private void setupDoorManager() {
        doorManager = usesBlockData() ? new DoorManagerBlock() : new DoorManagerMaterial();
    }

    public boolean usesBlockData() {
        return !version.equals("v1_7_R1")
                && !version.equals("v1_7_R2")
                && !version.equals("v1_7_R3")
                && !version.equals("v1_7_R4")
                && !version.equals("v1_8_R1")
                && !version.equals("v1_8_R2")
                && !version.equals("v1_8_R3")
                && !version.equals("v1_9_R1")
                && !version.equals("v1_9_R2")
                && !version.equals("v1_10_R1")
                && !version.equals("v1_11_R1")
                && !version.equals("v1_12_R1");
    }

    public boolean hasDoorEffects() {
        return !version.equals("v1_7_R1")
                && !version.equals("v1_7_R2")
                && !version.equals("v1_7_R3")
                && !version.equals("v1_7_R4")
                && !version.equals("v1_8_R1")
                && !version.equals("v1_8_R2")
                && !version.equals("v1_8_R3")
                && !version.equals("v1_9_R1");
    }

    public boolean hasSecondHand() {
        return !version.equals("v1_7_R1")
                && !version.equals("v1_7_R2")
                && !version.equals("v1_7_R3")
                && !version.equals("v1_7_R4")
                && !version.equals("v1_8_R1")
                && !version.equals("v1_8_R2")
                && !version.equals("v1_8_R3");
    }
}
