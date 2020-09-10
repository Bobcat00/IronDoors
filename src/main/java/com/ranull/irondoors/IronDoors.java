package com.ranull.irondoors;

import com.ranull.irondoors.listeners.PlayerInteractListener;
import com.ranull.irondoors.managers.DoorManager;
import com.ranull.irondoors.managers.DoorManagerBlock;
import com.ranull.irondoors.managers.DoorManagerMaterial;
import com.ranull.irondoors.nms.*;
import org.bukkit.plugin.java.JavaPlugin;

public class IronDoors extends JavaPlugin {
	private NMS nms;
	private DoorManager doorManager;
	private String version;

	@Override
	public void onEnable() {
		version = getServer().getClass().getPackage().getName().split("\\.")[3];

		setupNMS();
		setupDoorManager();

		getServer().getPluginManager().registerEvents(new PlayerInteractListener(this, doorManager, nms), this);
	}

	private boolean setupNMS() {
		try {
			if (version.equals("v1_7_R4")) {
				nms = new NMS_v1_7_R4();
			} else if (version.equals("v1_8_R3")) {
				nms = new NMS_v1_8_R3();
			} else if (version.equals("v1_9_R2")) {
				nms = new NMS_v1_9_R2();
			} else if (version.equals("v1_10_R1")) {
				nms = new NMS_v1_10_R1();
			} else if (version.equals("v1_11_R1")) {
				nms = new NMS_v1_11_R1();
			} else if (version.equals("v1_12_R1")) {
				nms = new NMS_v1_12_R1();
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
			}

			return nms != null;
		} catch (ArrayIndexOutOfBoundsException ignored) {
			return false;
		}
	}

	private void setupDoorManager() {
		if (usesBlockData()) {
			doorManager = new DoorManagerBlock();
		} else {
			doorManager = new DoorManagerMaterial();
		}
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
