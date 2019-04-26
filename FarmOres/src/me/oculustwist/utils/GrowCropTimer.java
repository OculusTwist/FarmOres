package me.oculustwist.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import me.oculustwist.config.FarmData;
import me.oculustwist.main.FarmOres;

public class GrowCropTimer extends BukkitRunnable {

	FarmOres plugin;

	public GrowCropTimer(FarmOres plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		FarmData fd = new FarmData("farmdata.yml");

		if (fd.exists()) {
			FileConfiguration f = fd.getConfig();

			int count = f.getInt("count");

			for (int i = 1; i <= count; i++) {

				int x = f.getInt("Farms.Location" + i + ".x");
				int y = f.getInt("Farms.Location" + i + ".y");
				int z = f.getInt("Farms.Location" + i + ".z");
				World w = Bukkit.getWorld(f.getString("Farms.Location" + i + ".world"));

				Location loc = new Location(w, x, y, z);

				if (!loc.equals(null)) {

					if (loc.getBlock().getType() == Material.CROPS) {
						GrowCrop.grow(w.getBlockAt(loc));
					}else if(loc.getBlock().getType() == Material.getMaterial(175)){
						if(loc.getBlock().getData() == 2 || loc.getBlock().getData() == 4){
							GrowCrop.grow(w.getBlockAt(loc));
						}
					}
				}

			}
		}
	}

}
