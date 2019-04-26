package me.oculustwist.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class WorldGuardUtils {
	
	public static WorldGuardPlugin getWorldGuard() {
	    Plugin worldGuard = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
	    if (worldGuard == null || !(worldGuard instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }
	 
	    return (WorldGuardPlugin) worldGuard;
	}

}
