package me.oculustwist.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.oculustwist.config.Config;
import me.oculustwist.config.FarmData;

public class EventBlockPlace implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {

		Block b = e.getBlock();
		Config c = new Config("config.yml");
		Player p = e.getPlayer();

		if (c.exists()) {

			FileConfiguration f = c.getConfig();

			if (b.getType() == Material.CROPS || b.getType() == Material.getMaterial(175)) {

				if (p.getItemInHand().hasItemMeta()) {
					String cobblename = ChatColor.translateAlternateColorCodes('&',
							f.getString("wheat.type.cobblestone.displayName"));
					String coalname = ChatColor.translateAlternateColorCodes('&',
							f.getString("wheat.type.coal.displayName"));
					String emeraldname = ChatColor.translateAlternateColorCodes('&',
							f.getString("wheat.type.emerald.displayName"));
					String ironname = ChatColor.translateAlternateColorCodes('&',
							f.getString("wheat.type.iron.displayName"));
					String lapisname = ChatColor.translateAlternateColorCodes('&',
							f.getString("wheat.type.lapis.displayName"));
					String diamondname = ChatColor.translateAlternateColorCodes('&',
							f.getString("wheat.type.diamond.displayName"));
					String redstonename = ChatColor.translateAlternateColorCodes('&',
							f.getString("wheat.type.redstone.displayName"));
					String goldname = ChatColor.translateAlternateColorCodes('&',
							f.getString("wheat.type.gold.displayName"));
					String expcrop1 = ChatColor.translateAlternateColorCodes('&',
							f.getString("wheat.type.expcrop1.displayName"));
					String expcrop2 = ChatColor.translateAlternateColorCodes('&',
							f.getString("wheat.type.expcrop2.displayName"));

					if (p.getItemInHand().getItemMeta().getDisplayName().equals(coalname)) {
						this.addLoc(b, p, "coal");
					} else if (p.getItemInHand().getItemMeta().getDisplayName().equals(emeraldname)) {
						this.addLoc(b, p, "emerald");
					} else if (p.getItemInHand().getItemMeta().getDisplayName().equals(cobblename)) {
						this.addLoc(b, p, "cobblestone");
					} else if (p.getItemInHand().getItemMeta().getDisplayName().equals(ironname)) {
						this.addLoc(b, p, "iron");
					} else if (p.getItemInHand().getItemMeta().getDisplayName().equals(lapisname)) {
						this.addLoc(b, p, "lapis");
					} else if (p.getItemInHand().getItemMeta().getDisplayName().equals(diamondname)) {
						this.addLoc(b, p, "diamond");
					} else if (p.getItemInHand().getItemMeta().getDisplayName().equals(redstonename)) {
						this.addLoc(b, p, "redstone");
					} else if (p.getItemInHand().getItemMeta().getDisplayName().equals(goldname)) {
						this.addLoc(b, p, "gold");
					} else if (p.getItemInHand().getItemMeta().getDisplayName().equals(expcrop1)) {
						this.addLoc(b, p, "expcrop1");
					} else if (p.getItemInHand().getItemMeta().getDisplayName().equals(expcrop2)) {
						this.addLoc(b, p, "expcrop2");
					}

				}

			} else {
				return;
			}

		}

	}

	public void addLoc(Block b, Player p, String type) {

		FarmData fd = new FarmData("farmdata.yml");

		if (fd.exists()) {

			FileConfiguration f = fd.getConfig();

			int x = b.getLocation().getBlockX();
			int y = b.getLocation().getBlockY();
			int z = b.getLocation().getBlockZ();

			String w = b.getLocation().getWorld().getName();

			int count = f.getInt("count") + 1;

			f.set("Farms.Location" + count + ".x", x);
			f.set("Farms.Location" + count + ".y", y);
			f.set("Farms.Location" + count + ".z", z);
			f.set("Farms.Location" + count + ".world", w);
			f.set("Farms.Location" + count + ".owner", p.getUniqueId().toString());
			f.set("Farms.Location" + count + ".type", type);

			f.set("count", count);

			fd.saveConfig();
		}

	}

}
