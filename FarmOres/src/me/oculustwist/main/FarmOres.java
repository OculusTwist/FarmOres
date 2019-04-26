package me.oculustwist.main;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitWorker;

import me.oculustwist.command.Cmd;
import me.oculustwist.config.Config;
import me.oculustwist.config.ConfigSetup;
import me.oculustwist.event.Evnt;
import me.oculustwist.permissions.Perm;
import me.oculustwist.utils.CheckCropTimer;
import me.oculustwist.utils.CropUtils;
import me.oculustwist.utils.GrowCropTimer;

public class FarmOres extends JavaPlugin {

	List<BukkitWorker> tasks;

	GrowCropTimer timer;
	CheckCropTimer timer2;

	@Override
	public void onEnable() {

		ConfigSetup.setupMessages();
		ConfigSetup.setupConfig();
		ConfigSetup.setupFarmData();

		setupTimer();
		timer2 = new CheckCropTimer(this);
		timer2.runTaskTimer(this, 5, 5);

		new Cmd(this);
		new Evnt(this);
		new CropUtils(this);
		new Perm(this);
	}

	@Override
	public void onDisable() {
	}

	public void setupTimer() {

		Config c = new Config("config.yml");
		FileConfiguration f = c.getConfig();

		timer = new GrowCropTimer(this);
		timer.runTaskTimer(this, 20 * f.getInt("growthDelay"), 20 * f.getInt("growthDelay"));
	}

}
