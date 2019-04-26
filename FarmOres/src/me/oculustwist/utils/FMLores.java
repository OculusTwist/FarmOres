package me.oculustwist.utils;

import org.bukkit.ChatColor;

public class FMLores {

	public static String getline() {
		String l = ChatColor.translateAlternateColorCodes('&',
				"&d&m-&7&m--&d&m-+-&7&m--&d&m-=&l+=+&d&m=-&7&m-&d&m-=&l+-=-+&d&m=-&7&m-&d&m-=&l+=+&d&m=-&7&m--&d&m-+-&7&m---&d&m-");
		return l;
	}

	public static String getPlaceDownText() {
		String pdt = ChatColor.translateAlternateColorCodes('&', "&7Place this down and mine to receive ores");
		return pdt;
	}

	public static String getPickupText() {
		String pt = ChatColor.translateAlternateColorCodes('&', "&7Shift mine to pickup the generator");
		return pt;
	}

}
