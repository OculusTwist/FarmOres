package me.oculustwist.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.oculustwist.config.FarmData;

public class EventPlayerInteraction implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerInteraction(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		Action a = e.getAction();
		
		FarmData fd = new FarmData("farmdata.yml");

		if(a == Action.RIGHT_CLICK_BLOCK){
			final Block block = e.getClickedBlock();
			
			if(fd.exists()){
				
				FileConfiguration f = fd.getConfig();
				int count = f.getInt("count");
				
			for(int i = 1; i <= count; i++ ){
				int x = f.getInt("Farms.Location" + i + ".x");
				int y = f.getInt("Farms.Location" + i + ".y");
				int z = f.getInt("Farms.Location" + i + ".z");

				World w = Bukkit.getServer().getWorld(f.getString("Farms.Location" + i + ".world"));

				Location loc = new Location(w, x, y, z);
				
				if(loc.equals(block.getLocation())){
					e.setCancelled(true);
				}
			}
			}
		}
		
		if (a == Action.PHYSICAL) {
			final Block block = e.getClickedBlock();
			if (block == null) {
				return;
			}
			final int blockType = block.getTypeId();
			if (blockType == Material.getMaterial(59).getId()) {
				e.setUseInteractedBlock(Event.Result.DENY);
				e.setCancelled(true);
				block.setTypeId(blockType);
				block.setData(block.getData());
			}
		}

		if (e.getAction() == Action.PHYSICAL) {
			final Block block = e.getClickedBlock();
			if (block == null) {
				return;
			}
			final int blockType = block.getTypeId();
			if (blockType == Material.getMaterial(60).getId()) {
				
				if(block.getData() == 0 || block.getData() == 7){
				e.setUseInteractedBlock(Event.Result.DENY);
				e.setCancelled(true);
				block.setType(Material.getMaterial(60));
				block.setData(block.getData());
				}
			}
		}

	}

}
