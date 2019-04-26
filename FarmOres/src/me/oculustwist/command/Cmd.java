package me.oculustwist.command;

import me.oculustwist.main.FarmOres;

public class Cmd {

	private static FarmOres plugin;

	public Cmd(FarmOres plugin) {
		Cmd.plugin = plugin;
		setup();
	}

	private static void setup() {

		plugin.getCommand("farmores").setExecutor(new CommandFarmOres());
		plugin.getCommand("seed").setExecutor(new CommandSeed());

	}

}
