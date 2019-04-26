package me.oculustwist.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.oculustwist.config.MessagesConfig;

public class Messages {

	private static MessagesConfig mc = new MessagesConfig("messages.yml");

	public static void noPerms(Player p) {
		if (mc.exists()) {
			FileConfiguration f = mc.getConfig();
			String s = ChatColor.translateAlternateColorCodes('&', f.getString("insufficientPerms"));
			p.sendMessage(s);
		}
	}

	public static void invalidSender(CommandSender sender) {
		if (mc.exists()) {
			FileConfiguration f = mc.getConfig();
			String s = ChatColor.translateAlternateColorCodes('&', f.getString("invalidSender"));
			sender.sendMessage(s);
		}
	}

	public static void invalidUsage(Player p, String usage) {
		if (mc.exists()) {
			FileConfiguration f = mc.getConfig();
			String s = ChatColor.translateAlternateColorCodes('&', f.getString("invalidUsage")).replace("%usage%",
					usage);
			p.sendMessage(s);
		}
	}

	public static void cropNotGrown(Player p) {
		if (mc.exists()) {
			FileConfiguration f = mc.getConfig();
			String s = ChatColor.translateAlternateColorCodes('&', f.getString("cropNotFullyGrown"));
			p.sendMessage(s);
		}
	}

	public static void cropRemoved(Player p) {
		if (mc.exists()) {
			FileConfiguration f = mc.getConfig();
			String s = ChatColor.translateAlternateColorCodes('&', f.getString("cropRemoved"));
			p.sendMessage(s);
		}
	}

	public static void playerNotFound(Player p) {
		if (mc.exists()) {
			FileConfiguration f = mc.getConfig();
			String s = ChatColor.translateAlternateColorCodes('&', f.getString("playerNotFound"));
			p.sendMessage(s);
		}
	}
	
	public static void notCropOwner(Player p) {
		if (mc.exists()) {
			FileConfiguration f = mc.getConfig();
			String s = ChatColor.translateAlternateColorCodes('&', f.getString("notCropOwner"));
			p.sendMessage(s);
		}
	}

	public static void fmNotFound(Player p) {
		if (mc.exists()) {
			FileConfiguration f = mc.getConfig();
			String s = ChatColor.translateAlternateColorCodes('&', f.getString("farmoreNotFound"));
			p.sendMessage(s);
		}
	}

	public static void seedAdded(Player p) {
		if (mc.exists()) {
			FileConfiguration f = mc.getConfig();
			String s = ChatColor.translateAlternateColorCodes('&', f.getString("seedAdded"));
			p.sendMessage(s);
		}
	}

	public static void cannotBreak(Player p) {
		if (mc.exists()) {
			FileConfiguration f = mc.getConfig();
			String s = ChatColor.translateAlternateColorCodes('&', f.getString("cannotBreak"));
			p.sendMessage(s);
		}
	}

	public static void punchBase(Player p) {
		if (mc.exists()) {
			FileConfiguration f = mc.getConfig();
			String s = ChatColor.translateAlternateColorCodes('&', f.getString("punchBase"));
			p.sendMessage(s);
		}
	}

	public static void genRemoved(Player p) {
		if (mc.exists()) {
			FileConfiguration f = mc.getConfig();
			String s = ChatColor.translateAlternateColorCodes('&', f.getString("generatorRemoved"));
			p.sendMessage(s);
		}
	}

}
