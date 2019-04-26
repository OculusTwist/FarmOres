package me.oculustwist.event;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import me.oculustwist.main.FarmOres;

public class Evnt {

	static FarmOres plugin;

	public Evnt(FarmOres plugin) {
		Evnt.plugin = plugin;
		setup();
	}

	public static void setup() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new EventBlockBreak(plugin), plugin);
		pm.registerEvents(new EventBlockPlace(), plugin);
		pm.registerEvents(new EventPlayerInteraction(), plugin);
	}

}
