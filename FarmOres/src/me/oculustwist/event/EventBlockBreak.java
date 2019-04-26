package me.oculustwist.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.oculustwist.config.FarmData;
import me.oculustwist.main.FarmOres;
import me.oculustwist.utils.CropUtils;
import me.oculustwist.utils.Messages;

public class EventBlockBreak implements Listener {

	FarmOres plugin;

	public EventBlockBreak(FarmOres plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {

		Player p = e.getPlayer();

		Block b = e.getBlock();
		int data = b.getData();
		FarmData fd = new FarmData("farmdata.yml");

		if (fd.exists()) {

			FileConfiguration f = fd.getConfig();

			int count = f.getInt("count");

			for (int i = 1; i <= count; i++) {
				int x = f.getInt("Farms.Location" + i + ".x");
				int y = f.getInt("Farms.Location" + i + ".y");
				int z = f.getInt("Farms.Location" + i + ".z");
				
				String owner = f.getString("Farms.Location" + i + ".owner");

				String type = f.getString("Farms.Location" + i + ".type");

				World w = Bukkit.getServer().getWorld(f.getString("Farms.Location" + i + ".world"));

				Location loc = new Location(w, x, y, z);

				if ((b.getType() == Material.LONG_GRASS || b.getType() == Material.DOUBLE_PLANT)
						&& b.getLocation().subtract(0, 1, 0).equals(loc)) {

					Messages.punchBase(p);
					e.setCancelled(true);
				}

				if (!loc.equals(null)) {
					if (loc.equals(b.getLocation())) {
						if(owner.equalsIgnoreCase(p.getUniqueId().toString())){
						if (loc.getBlock().getType() == Material.CROPS || loc.getBlock().getType() == Material.getMaterial(175) || loc.getBlock().getType() == Material.DOUBLE_PLANT) {
							if (type.equalsIgnoreCase("coal")) {
								CropUtils.doCropEvent(data, e, b, p, "coal");
							} else if (type.equalsIgnoreCase("cobblestone")) {
								CropUtils.doCropEvent(data, e, b, p, "cobblestone");
							} else if (type.equalsIgnoreCase("emerald")) {
								CropUtils.doCropEvent(data, e, b, p, "emerald");
							} else if (type.equalsIgnoreCase("iron")) {
								CropUtils.doCropEvent(data, e, b, p, "iron");
							} else if (type.equalsIgnoreCase("lapis")) {
								CropUtils.doCropEvent(data, e, b, p, "lapis");
							} else if (type.equalsIgnoreCase("diamond")) {
								CropUtils.doCropEvent(data, e, b, p, "diamond");
							} else if (type.equalsIgnoreCase("redstone")) {
								CropUtils.doCropEvent(data, e, b, p, "redstone");
							} else if (type.equalsIgnoreCase("gold")) {
								CropUtils.doCropEvent(data, e, b, p, "gold");
							} else if (type.equalsIgnoreCase("expcrop1")) {
								CropUtils.doCustomCropEvent(e, b, p, "expcrop1", type);
							} else if (type.equalsIgnoreCase("expcrop2")) {
								CropUtils.doCustomCropEvent(e, b, p, "expcrop2", type);
							}
						}
						
					}else{
						e.setCancelled(true);
						Messages.notCropOwner(p);
					}

					} else if (loc.subtract(0, 1, 0).equals(b.getLocation())) {
						e.setCancelled(true);
						Messages.cannotBreak(p);
					}
				}
			}

		}
	}

}
