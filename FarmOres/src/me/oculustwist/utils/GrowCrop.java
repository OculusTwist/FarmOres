package me.oculustwist.utils;

import org.bukkit.Bukkit;
import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.oculustwist.config.FarmData;
import net.md_5.bungee.api.ChatColor;

public class GrowCrop {

	@SuppressWarnings("deprecation")
	public static void grow(Block crop) {

		int data = crop.getData();

		if (crop.getType() == Material.CROPS) {
			if (data == 0) {
				crop.setData(CropState.GERMINATED.getData());
			} else if (data == 1) {
				crop.setData(CropState.VERY_SMALL.getData());
			} else if (data == 2) {
				crop.setData(CropState.SMALL.getData());
			} else if (data == 3) {
				crop.setData(CropState.MEDIUM.getData());
			} else if (data == 4) {
				crop.setData(CropState.TALL.getData());
			} else if (data == 5) {
				crop.setData(CropState.VERY_TALL.getData());
			} else if (data == 6) {
				crop.setData(CropState.RIPE.getData());
			} else if (data == 7) {
				return;
			} else {
				FMLogger.getLogger().info(ChatColor.RED + "Failed to grow crop at X: " + crop.getLocation().getBlockX()
						+ " Y: " + crop.getLocation().getBlockY() + " Z: " + crop.getLocation().getBlockZ());
			}
		} else if (crop.getType() == Material.getMaterial(175)) {

			if (crop.getData() == 2) {
				FarmData fd = new FarmData("farmdata.yml");
				
				if (fd.exists()) {

					FileConfiguration f = fd.getConfig();

					int count = f.getInt("count");
					for (int i = 1; i <= count; i++) {
						int x = f.getInt("Farms.Location" + i + ".x");
						int y = f.getInt("Farms.Location" + i + ".y");
						int z = f.getInt("Farms.Location" + i + ".z");

						String type = f.getString("Farms.Location" + i + ".type");

						World w = Bukkit.getServer().getWorld(f.getString("Farms.Location" + i + ".world"));

						Location loc = new Location(w, x, y, z);

						if (loc.equals(crop.getLocation())) {
							
							if (type.equalsIgnoreCase("expcrop1")) {
								crop.setType(Material.DOUBLE_PLANT);
							} else if (type.equalsIgnoreCase("expcrop2")) {
								crop.setType(Material.DOUBLE_PLANT);
								crop.setData((byte) 4);
							}
						}
					}
				}
			}
		}
	}

}
