package me.oculustwist.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.oculustwist.config.FarmData;
import me.oculustwist.main.FarmOres;

public class CheckCropTimer extends BukkitRunnable {

	FarmOres plugin;

	public CheckCropTimer(FarmOres plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		FarmData fd = new FarmData("farmdata.yml");

		if (fd.exists()) {
			FileConfiguration f = fd.getConfig();

			int count = f.getInt("count");

			for (int i = 1; i <= count; i++) {
				
				
				if(f.getString("Farms.Location" + i + ".world") != null && f.getString("Farms.Location" + i + ".x") != null && f.getString("Farms.Location" + i + ".y") != null && f.getString("Farms.Location" + i + ".z") != null){
					World w = Bukkit.getWorld(f.getString("Farms.Location" + i + ".world"));
					int x = f.getInt("Farms.Location" + i + ".x");
					int y = f.getInt("Farms.Location" + i + ".y");
				    int z = f.getInt("Farms.Location" + i + ".z");
				    
					Location loc = new Location(w, x, y, z);

					if (loc.getBlock().getType() != Material.CROPS && loc.getBlock().getType() != Material.getMaterial(175) && loc.getBlock().getType() != Material.DOUBLE_PLANT || loc == null) {
						f.set("Farms.Location" + i, null);
						fd.saveConfig();
						ConfigUtils.reorderConfig();
					}
				}else{
					ConfigUtils.reorderConfig();
					return;
				}


			}
		}
	}

}
