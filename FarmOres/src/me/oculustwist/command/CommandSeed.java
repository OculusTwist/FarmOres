package me.oculustwist.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.oculustwist.config.Config;
import me.oculustwist.utils.CropUtils;
import me.oculustwist.utils.FMLogger;
import me.oculustwist.utils.FMLores;
import me.oculustwist.utils.Messages;
import net.md_5.bungee.api.ChatColor;

public class CommandSeed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		// If the sender is a player
		if (sender instanceof Player) {

			Player p = (Player) sender;
			int length = args.length;
			String usage = "/seed give <player> <type> <amount> | /seed list";
			Config c = new Config("config.yml");

			// If the command is seeds
			if (cmd.getName().equalsIgnoreCase("seed")) {

				// Check if the length of the command is 1
				if (length == 4) {

					if (args[0].toString().equalsIgnoreCase("give")) {

						if(p.hasPermission("farmores.give")){
						if (Bukkit.getPlayer(args[1].toString()) != null) {

							Player target = Bukkit.getPlayer(args[1].toString());

							if (args[2].toString().equalsIgnoreCase("cobblestone")
									|| args[2].toString().equalsIgnoreCase("coal")
									|| args[2].toString().equalsIgnoreCase("iron")
									|| args[2].toString().equalsIgnoreCase("emerald")
									|| args[2].toString().equalsIgnoreCase("lapis")
									|| args[2].toString().equalsIgnoreCase("diamond")
									|| args[2].toString().equalsIgnoreCase("redstone")
									|| args[2].toString().equalsIgnoreCase("gold")
									|| args[2].toString().equalsIgnoreCase("expcrop1")
									|| args[2].toString().equals("expcrop2")) {

								if (c.exists()) {

									FileConfiguration f = c.getConfig();

									ItemStack i = CropUtils.getSeed(args[2].toString(), p);

									if (i != null) {
										target.getInventory().addItem(i);
									}
									Messages.seedAdded(p);

								} else {
									FMLogger.getLogger().info(ChatColor.RED
											+ "Error getting config.yml! Delete all files and reinstall the plugin.");
								}

							} else {
								Messages.fmNotFound(p);
							}

						} else {
							Messages.playerNotFound(p);
						}

						}else{
							Messages.noPerms(p);
						}
						
					} else {
						Messages.invalidUsage(p, usage);
					}

				} else if (length == 1) {
					if (args[0].toString().equalsIgnoreCase("list")) {
						
						if(p.hasPermission("farmores.list")){

						p.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "------------" + ChatColor.WHITE
								+ "" + ChatColor.BOLD + " Seeds " + ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH
								+ "------------");
						p.sendMessage(ChatColor.AQUA + "Cobblestone");
						p.sendMessage(ChatColor.AQUA + "Coal");
						p.sendMessage(ChatColor.AQUA + "Iron");
						p.sendMessage(ChatColor.AQUA + "Redstone");
						p.sendMessage(ChatColor.AQUA + "Gold");
						p.sendMessage(ChatColor.AQUA + "Lapis");
						p.sendMessage(ChatColor.AQUA + "Diamond");
						p.sendMessage(ChatColor.AQUA + "Emerald");
						p.sendMessage(ChatColor.AQUA + "EXPCrop1");
						p.sendMessage(ChatColor.AQUA + "EXPCrop2");
						p.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "------------------------------");

						}else{
							Messages.noPerms(p);
						}
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
