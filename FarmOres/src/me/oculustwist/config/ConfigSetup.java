package me.oculustwist.config;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigSetup {

	public static void setupMessages() {

		MessagesConfig mc = new MessagesConfig("messages.yml");

		// Check if config exists
		if (!mc.exists()) {

			FileConfiguration f = mc.getConfig();
			f.set("insufficientPerms", "&c&lERROR &r&e&l> &r&4Insufficient permissions!");
			f.set("invalidSender", "&c&lERROR &r&e&l> &r&4You must be a player to use this command!");
			f.set("invalidUsage", "&c&lERROR &r&e&l> &r&4Incorrect usage! Usage: %usage%");
			f.set("cropNotFullyGrown", "&cThat crop has not grown enough to be harvested!");
			f.set("cropRemoved", "&aCropOre has been successfully removed!");
			f.set("seedAdded", "&aA FarmOre seed has been successfully added to your inventory!");
			f.set("playerNotFound", "&c&lERROR &r&e&l> &r&4Player not found!");
			f.set("farmoreNotFound", "&c&lERROR &r&e&l> &r&4FarmOre not found!");
			f.set("cannotBreak", "&c&lERROR &r&e&l> &r&4You cannot break that block as it is below a farmore!");
			f.set("punchBase", "&cPunch the source of this generator to harvest it!");
			f.set("generatorRemoved", "&aOre Generator successfully removed!");
			f.set("notCropOwner", "&cYou must be the owner of this crop to break it!");

			mc.saveConfig();

		}

	}

	public static void setupConfig() {

		Config c = new Config("config.yml");

		if (!c.exists()) {

			FileConfiguration f = c.getConfig();

			f.set("growthDelay", 5);

			f.set("wheat.type.coal.displayName", "&7&lCoal Seed &8&l(&7Place&8&l)");
			f.set("wheat.type.emerald.displayName", "&7&lEmerald Seed &8&l(&7Place&8&l)");
			f.set("wheat.type.cobblestone.displayName", "&7&lCobblestone Seed &8&l(&7Place&8&l)");
			f.set("wheat.type.iron.displayName", "&7&lIron Seed &8&l(&7Place&8&l)");
			f.set("wheat.type.lapis.displayName", "&7&lLapis Seed &8&l(&7Place&8&l)");
			f.set("wheat.type.diamond.displayName", "&7&lDiamond Seed &8&l(&7Place&8&l)");
			f.set("wheat.type.redstone.displayName", "&7&lRedstone Seed &8&l(&7Place&8&l)");
			f.set("wheat.type.gold.displayName", "&7&lGold Seed &8&l(&7Place&8&l)");
			f.set("wheat.type.expcrop1.displayName", "&7&lExpCrop1 Seed &8&l(&7Place&8&l)");
			f.set("wheat.type.expcrop2.displayName", "&7&lExpCrop2 Seed &8&l(&7Place&8&l)");
			f.set("wheat.type.expcrop1.exp", 100);
			f.set("wheat.type.expcrop2.exp", 100);

			c.saveConfig();

		}

	}

	public static void setupFarmData() {

		FarmData fd = new FarmData("farmdata.yml");

		if (!fd.exists()) {

			FileConfiguration f = fd.getConfig();

			f.set("count", 0);

			fd.saveConfig();

		}

	}

	public static void setupAll() {
		setupFarmData();
		setupConfig();
		setupMessages();
	}

}
