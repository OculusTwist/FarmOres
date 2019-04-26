package me.oculustwist.utils;

import net.md_5.bungee.api.ChatColor;

public class ColorUtils {

	public static String format(String s) {
		String output = ChatColor.translateAlternateColorCodes('&', s);
		return output;
	}

}
