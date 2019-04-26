package me.oculustwist.utils;

import java.util.Arrays;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;

import me.oculustwist.config.Config;
import me.oculustwist.config.FarmData;
import me.oculustwist.main.FarmOres;
import net.md_5.bungee.api.ChatColor;

public class CropUtils {

	static FarmOres plugin;

	public CropUtils(FarmOres plugin) {
		CropUtils.plugin = plugin;
	}

	static Config c = new Config("config.yml");
	static FarmData fd = new FarmData("farmdata.yml");

	public static void doCropEvent(int data, BlockBreakEvent e, Block b, Player p, String item) {

		if (c.exists()) {

			if (fd.exists()) {
				
				WorldGuardPlugin worldGuard;
				boolean canbuild;
				if(WorldGuardUtils.getWorldGuard() != null){
				worldGuard = WorldGuardUtils.getWorldGuard();
				canbuild = worldGuard.canBuild(p, b);
				}else{
					worldGuard = null;
					canbuild = false;
				}

				// FileConfiguration f = c.getConfig();
				// FileConfiguration fc = fd.getConfig();
				if (data == 0 || data == 1) {

					if (!p.isSneaking()) {
						Messages.cropNotGrown(p);
						e.setCancelled(true);
						b.getDrops().clear();
					} else {
						b.getDrops().clear();
						if(worldGuard != null && canbuild){
							ItemStack i = getSeed(item, p);
							p.getWorld().dropItemNaturally(b.getLocation(), i);
							ConfigUtils.deleteCrop(b, p);
						}else if (worldGuard != null && !canbuild){
							return;
						} else {
							ItemStack i = getSeed(item, p);
							p.getWorld().dropItemNaturally(b.getLocation(), i);
							ConfigUtils.deleteCrop(b, p);
						}

					}

				} else if (data == 2 || data == 3) {

					if (!p.isSneaking()) {
						int dropAmount = 1;

						ItemStack i = null;
						if (item.equalsIgnoreCase("lapis")) {
							i = new ItemStack(Material.INK_SACK, dropAmount, (short) 4);
						} else if (item.equalsIgnoreCase("iron")) {
							i = new ItemStack(Material.IRON_INGOT, dropAmount);
						} else if (item.equalsIgnoreCase("gold")) {
							i = new ItemStack(Material.GOLD_INGOT, dropAmount);
						} else if (item.equalsIgnoreCase("cobblestone")) {
							i = new ItemStack(Material.getMaterial(4), dropAmount);
						} else {
							i = new ItemStack(Material.getMaterial(item.toUpperCase()), dropAmount);
						}
						e.setCancelled(true);
						doCropBreak(b, i);
					} else {
						b.getDrops().clear();
						if(worldGuard != null && canbuild){
							ItemStack i = getSeed(item, p);
							p.getWorld().dropItemNaturally(b.getLocation(), i);
							ConfigUtils.deleteCrop(b, p);
						}else if (worldGuard != null && !canbuild){
							return;
						} else {
							ItemStack i = getSeed(item, p);
							p.getWorld().dropItemNaturally(b.getLocation(), i);
							ConfigUtils.deleteCrop(b, p);
						}
					}
				} else if (data == 4 || data == 5 || data == 6) {

					if (!p.isSneaking()) {
						int dropAmount = 2;

						ItemStack i = null;
						if (item.equalsIgnoreCase("lapis")) {
							i = new ItemStack(Material.INK_SACK, dropAmount, (short) 4);
						} else if (item.equalsIgnoreCase("gold")) {
							i = new ItemStack(Material.GOLD_INGOT, dropAmount);
						} else if (item.equalsIgnoreCase("cobblestone")) {
							i = new ItemStack(Material.getMaterial(4), dropAmount);
						} else if (item.equalsIgnoreCase("iron")) {
							i = new ItemStack(Material.IRON_INGOT, dropAmount);
						} else {
							i = new ItemStack(Material.getMaterial(item.toUpperCase()), dropAmount);
						}
						e.setCancelled(true);
						doCropBreak(b, i);
					} else {
						b.getDrops().clear();
						if(worldGuard != null && canbuild){
							ItemStack i = getSeed(item, p);
							p.getWorld().dropItemNaturally(b.getLocation(), i);
							ConfigUtils.deleteCrop(b, p);
						}else if (worldGuard != null && !canbuild){
							return;
						} else {
							ItemStack i = getSeed(item, p);
							p.getWorld().dropItemNaturally(b.getLocation(), i);
							ConfigUtils.deleteCrop(b, p);
						}
					}
				} else {
					if (!p.isSneaking()) {
						int dropAmount = 3;

						ItemStack i = null;
						if (item.equalsIgnoreCase("lapis")) {
							i = new ItemStack(Material.INK_SACK, dropAmount, (short) 4);
						} else if (item.equalsIgnoreCase("iron")) {
							i = new ItemStack(Material.IRON_INGOT, dropAmount);
						} else if (item.equalsIgnoreCase("cobblestone")) {
							i = new ItemStack(Material.getMaterial(4), dropAmount);
						} else if (item.equalsIgnoreCase("gold")) {
							i = new ItemStack(Material.GOLD_INGOT, dropAmount);
						} else {
							i = new ItemStack(Material.getMaterial(item.toUpperCase()), dropAmount);
						}

						e.setCancelled(true);
						doCropBreak(b, i);
					} else {
						b.getDrops().clear();
						if(worldGuard != null && canbuild){
							ItemStack i = getSeed(item, p);
							p.getWorld().dropItemNaturally(b.getLocation(), i);
							ConfigUtils.deleteCrop(b, p);
						}else if (worldGuard != null && !canbuild){
							return;
						} else {
							ItemStack i = getSeed(item, p);
							p.getWorld().dropItemNaturally(b.getLocation(), i);
							ConfigUtils.deleteCrop(b, p);
						}
					}
				}

			}
		}
	}

	public static void doCustomCropEvent(BlockBreakEvent e, Block b, Player p, String item, String type) {

		if (c.exists()) {

			if (fd.exists()) {
				
				FileConfiguration f = c.getConfig();
				FileConfiguration fc = fd.getConfig();

				WorldGuardPlugin worldGuard = WorldGuardUtils.getWorldGuard();
				boolean canbuild = worldGuard.canBuild(p, b);
				
				
				
				int exp = f.getInt("wheat.type." + type + ".exp");
				
				if (type.equalsIgnoreCase("expcrop1")) {
					if (b.getType() == Material.getMaterial(175) && b.getData() == 2) {

						if (!p.isSneaking()) {
							Messages.cropNotGrown(p);
							e.setCancelled(true);
							b.getDrops().clear();
						} else {
							b.getDrops().clear();
							if(worldGuard != null && canbuild){
								ItemStack i = getSeed(item, p);
								p.getWorld().dropItemNaturally(b.getLocation(), i);
								ConfigUtils.deleteCrop(b, p);
							}else if (worldGuard != null && !canbuild){
								return;
							} else {
								ItemStack i = getSeed(item, p);
								p.getWorld().dropItemNaturally(b.getLocation(), i);
								ConfigUtils.deleteCrop(b, p);
							}
						}

					} else if (b.getType() == Material.DOUBLE_PLANT) {
						if(!p.isSneaking()){
							
						
							
						p.giveExp(exp);
						b.setType(Material.getMaterial(175));
						b.setData((byte) 2);
						b.getDrops().clear();
						e.setCancelled(true);
						}else{
							b.getDrops().clear();
							if(worldGuard != null && canbuild){
								ItemStack i = getSeed(item, p);
								p.getWorld().dropItemNaturally(b.getLocation(), i);
								ConfigUtils.deleteCrop(b, p);
							}else if (worldGuard != null && !canbuild){
								return;
							} else {
								ItemStack i = getSeed(item, p);
								p.getWorld().dropItemNaturally(b.getLocation(), i);
								ConfigUtils.deleteCrop(b, p);
							}
						}
					}
				
				}else if(type.equalsIgnoreCase("expcrop2")){
					if (b.getType() == Material.getMaterial(175) && b.getData() == 2) {

						if (!p.isSneaking()) {
							Messages.cropNotGrown(p);
							e.setCancelled(true);
							b.getDrops().clear();
						} else {
							b.getDrops().clear();
							if(worldGuard != null && canbuild){
								ItemStack i = getSeed(item, p);
								p.getWorld().dropItemNaturally(b.getLocation(), i);
								ConfigUtils.deleteCrop(b, p);
							}else if (worldGuard != null && !canbuild){
								return;
							} else {
								ItemStack i = getSeed(item, p);
								p.getWorld().dropItemNaturally(b.getLocation(), i);
								ConfigUtils.deleteCrop(b, p);
							}
						}

					} else if (b.getType() == Material.DOUBLE_PLANT) {
						if(!p.isSneaking()){
						p.giveExp(exp);
						b.setType(Material.getMaterial(175));
						b.setData((byte) 2);
						b.getDrops().clear();
						e.setCancelled(true);
						}else{
							b.getDrops().clear();
							if(worldGuard != null && canbuild){
								ItemStack i = getSeed(item, p);
								p.getWorld().dropItemNaturally(b.getLocation(), i);
								ConfigUtils.deleteCrop(b, p);
							}else if (worldGuard != null && !canbuild){
								return;
							} else {
								ItemStack i = getSeed(item, p);
								p.getWorld().dropItemNaturally(b.getLocation(), i);
								ConfigUtils.deleteCrop(b, p);
							}
						}
					}
				}
			}

		}
	}

	@SuppressWarnings("deprecation")
	public static void doCropBreak(Block b, ItemStack item) {
		b.setData(CropState.SEEDED.getData());
		b.getDrops().clear();
		b.getWorld().dropItemNaturally(b.getLocation(), item);
	}

	public static ItemStack getSeed(String seedName, Player p) {

		Config c = new Config("config.yml");

		if (c.exists()) {

			FileConfiguration f = c.getConfig();
			String name = f.getString("wheat.type." + seedName.toString() + ".displayName");

			ItemStack i = null;
			if (seedName.toString().equalsIgnoreCase("coal")) {
				i = new ItemStack(Material.SEEDS, 1);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
				im.setLore(Arrays.asList(FMLores.getline(), FMLores.getPlaceDownText(), FMLores.getline(),
						FMLores.getPickupText(), FMLores.getline()));
				im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				i.setItemMeta(im);
			} else if (seedName.toString().equalsIgnoreCase("cobblestone")) {
				i = new ItemStack(Material.SEEDS, 1);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
				im.setLore(Arrays.asList(FMLores.getline(), FMLores.getPlaceDownText(), FMLores.getline(),
						FMLores.getPickupText(), FMLores.getline()));
				im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				i.setItemMeta(im);
			} else if (seedName.toString().toString().equalsIgnoreCase("iron")) {
				i = new ItemStack(Material.SEEDS, 1);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
				im.setLore(Arrays.asList(FMLores.getline(), FMLores.getPlaceDownText(), FMLores.getline(),
						FMLores.getPickupText(), FMLores.getline()));
				im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				i.setItemMeta(im);
			} else if (seedName.toString().toString().equalsIgnoreCase("emerald")) {
				i = new ItemStack(Material.SEEDS, 1);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
				im.setLore(Arrays.asList(FMLores.getline(), FMLores.getPlaceDownText(), FMLores.getline(),
						FMLores.getPickupText(), FMLores.getline()));
				im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				i.setItemMeta(im);
			} else if (seedName.toString().toString().equalsIgnoreCase("lapis")) {
				i = new ItemStack(Material.SEEDS, 1);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
				im.setLore(Arrays.asList(FMLores.getline(), FMLores.getPlaceDownText(), FMLores.getline(),
						FMLores.getPickupText(), FMLores.getline()));
				im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				i.setItemMeta(im);
			} else if (seedName.toString().toString().equalsIgnoreCase("diamond")) {
				i = new ItemStack(Material.SEEDS, 1);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
				im.setLore(Arrays.asList(FMLores.getline(), FMLores.getPlaceDownText(), FMLores.getline(),
						FMLores.getPickupText(), FMLores.getline()));
				im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				i.setItemMeta(im);
			} else if (seedName.toString().toString().equalsIgnoreCase("redstone")) {
				i = new ItemStack(Material.SEEDS, 1);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
				im.setLore(Arrays.asList(FMLores.getline(), FMLores.getPlaceDownText(), FMLores.getline(),
						FMLores.getPickupText(), FMLores.getline()));
				im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				i.setItemMeta(im);
			} else if (seedName.toString().toString().equalsIgnoreCase("gold")) {
				i = new ItemStack(Material.SEEDS, 1);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
				im.setLore(Arrays.asList(FMLores.getline(), FMLores.getPlaceDownText(), FMLores.getline(),
						FMLores.getPickupText(), FMLores.getline()));
				im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				i.setItemMeta(im);
			} else if (seedName.toString().toString().equalsIgnoreCase("expcrop1")) {
				i = new ItemStack(Material.getMaterial(175), 1, (short) 2);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
				im.setLore(Arrays.asList(FMLores.getline(), FMLores.getPlaceDownText(), FMLores.getline(),
						FMLores.getPickupText(), FMLores.getline()));
				im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				i.setItemMeta(im);
			} else if (seedName.toString().toString().equalsIgnoreCase("expcrop2")) {
				i = new ItemStack(Material.getMaterial(175), 1, (short) 2);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
				im.setLore(Arrays.asList(FMLores.getline(), FMLores.getPlaceDownText(), FMLores.getline(),
						FMLores.getPickupText(), FMLores.getline()));
				im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
				i.setItemMeta(im);
			} else {
				i = null;
				Messages.fmNotFound(p);
				FMLogger.getLogger().info(ChatColor.RED + "Error retrieving farmore type!");
			}

			return i;
		} else {
			FMLogger.getLogger()
					.info(ChatColor.RED + "Error retrieving configuration file to get the seed! Reinstall the plugin.");
			return null;
		}
	}

}
