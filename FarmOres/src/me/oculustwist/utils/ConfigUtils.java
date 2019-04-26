package me.oculustwist.utils;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.oculustwist.config.FarmData;

public class ConfigUtils {

	public static void reorderConfig() {
		FarmData fd = new FarmData("farmdata.yml");

		if (fd.exists()) {
			FileConfiguration f = fd.getConfig();

			for (int i = 1; i <= f.getInt("count"); i++) {

				int get = i + 1;

				if (f.getConfigurationSection("Farms.Location" + i) == null
						&& f.getConfigurationSection("Farms.Location" + get) != null) {
					
					Map<String, Object> vals = f.getConfigurationSection("Farms.Location" + get).getValues(true);
					f.set("Farms.Location" + i, vals);
					f.set("Farms.Location" + get, null);
					f.set("count", f.getConfigurationSection("Farms").getKeys(false).size());
					fd.saveConfig();
				} else if (f.getConfigurationSection("Farms.Location" + i) == null
						&& f.getConfigurationSection("Farms.Location" + get) == null) {

					f.set("count", f.getConfigurationSection("Farms").getKeys(false).size());
					fd.saveConfig();

				} else {
					Map<String, Object> vals = f.getConfigurationSection("Farms.Location" + i).getValues(true);
					f.set("Farms.Location" + i, vals);
					fd.saveConfig();
				}
			}

		}
	}

	public static void deleteCrop(Block b, Player p) {

		FarmData fd = new FarmData("farmdata.yml");

		if (fd.exists()) {

			FileConfiguration f = fd.getConfig();

			int count = f.getInt("count");

			for (int i = 1; i <= count; i++) {
				int x = f.getInt("Farms.Location" + i + ".x");
				int y = f.getInt("Farms.Location" + i + ".y");
				int z = f.getInt("Farms.Location" + i + ".z");
				World w = Bukkit.getServer().getWorld(f.getString("Farms.Location" + i + ".world"));

				Location loc = new Location(w, x, y, z);

				if (b.getLocation().equals(loc)) {
					f.set("Farms.Location" + i, null);
					fd.saveConfig();
					ConfigUtils.reorderConfig();
					Messages.genRemoved(p);
					b.getDrops().clear();
				}
			}
		}
	}

}
