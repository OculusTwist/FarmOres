package me.oculustwist.permissions;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

import me.oculustwist.main.FarmOres;

public class Perm {
	
	static FarmOres plugin;
	
	public Perm(FarmOres plugin){
		Perm.plugin = plugin;
		setup();
	}
	
		static Permission pm1 = new Permission("farmores.give");
		static Permission pm2 = new Permission("farmores.reload");
		static Permission pm3 = new Permission("farmores.list");
	
	public static void setup(){
		PluginManager p = Bukkit.getPluginManager();
		
		p.addPermission(pm1);
		p.addPermission(pm2);
		p.addPermission(pm3);
	}

}
