package me.oculustwist.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.oculustwist.config.Config;
import me.oculustwist.config.ConfigSetup;
import me.oculustwist.config.FarmData;
import me.oculustwist.config.MessagesConfig;
import me.oculustwist.utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class CommandFarmOres implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		// If the sender is a player
		if (sender instanceof Player) {

			Player p = (Player) sender;
			int length = args.length;
			String usage = "/farmores reload/info";
			MessagesConfig mc = new MessagesConfig("messages.yml");
			FarmData fd = new FarmData("farmdata.yml");
			Config c = new Config("config.yml");

			// If the command is farmores
			if (cmd.getName().equalsIgnoreCase("farmores")) {

				// Check if the length of the command is 1
				if (length == 1) {

					// If the first argument is reload
					if (args[0].equalsIgnoreCase("reload")) {
						
						if(p.hasPermission("farmores.reload")){
						mc.reload();
						c.reload();
						fd.reload();
						ConfigSetup.setupAll();
						p.sendMessage(ChatColor.GREEN + "FarmOres successfully reloaded!");
						}else{
							Messages.noPerms(p);
						}
					} else if (args[0].equalsIgnoreCase("info")) {
						p.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "-------------------------");
						p.sendMessage(ChatColor.AQUA + "Plugin Name: " + ChatColor.WHITE + "FarmOres");
						p.sendMessage(ChatColor.AQUA + "Plugin Version: " + ChatColor.WHITE + "1.7");
						p.sendMessage(ChatColor.AQUA + "Developer: " + ChatColor.WHITE + "OculusTwist");
						p.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "-------------------------");
					} else {
						Messages.invalidUsage(p, usage);
					}

				} else {
					// If the length of the command is not 1
					Messages.invalidUsage(p, usage);
				}

				return true;
			}

		} else {
			// If the sender is not a player
			Messages.invalidSender(sender);
		}

		return false;
	}

}
