package me.oculustwist.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FarmData {

	private FileConfiguration fc;
	private File file;
	private final JavaPlugin plugin = JavaPlugin.getProvidingPlugin(this.getClass());
	private static List<FarmData> configs = new ArrayList<>();

	public FarmData(String n) {
		configs.add(this);
	}

	/**
	 * Returns an instanceof the JavaPlugin.
	 * 
	 * @return
	 */
	public JavaPlugin getInstance() {
		if (plugin == null)
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return plugin;
	}

	/**
	 * Get a config from type 'Config'. If it doesn't exist it will create a new
	 * Config. NOTE: String n must be exactly the Config's name.
	 * 
	 * @param n
	 *            The Name of the config found by getName()
	 * @return Config for given name.
	 */
	public static FarmData getConfig(String n) {
		for (FarmData c : configs) {
			return c;
		}
		return new FarmData(n);
	}

	/**
	 * Deletes the file
	 * 
	 * @return True if the config was successfully deleted. If anything went
	 *         wrong it returns false
	 */
	public boolean delete() {
		return getFile().delete();
	}

	/**
	 * Checks to make sure the config is null or not. This is only a check and
	 * it wont create the config.
	 * 
	 * @return True if it exists & False if it doesn't
	 */
	public boolean exists() {
		if (fc == null || file == null) {
			File temp = new File(getDataFolder(), "farmdata.yml");
			if (!temp.exists()) {
				return false;
			}
			file = temp;
		}
		return true;
	}

	/**
	 * Gets the plugin's folder. If none exists it will create it.
	 * 
	 * @return The folder as type java.io.File
	 */
	public File getDataFolder() {
		File dir = new File(
				FarmData.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " "));
		File d = new File(dir.getParentFile().getPath(), getInstance().getName());
		if (!d.exists()) {
			d.mkdirs();
		}
		return d;
	}

	/**
	 * Gets the File for the owner
	 * 
	 * @return The File as type java.io.File
	 */
	public File getFile() {
		if (file == null) {
			file = new File(getDataFolder(), "farmdata.yml");
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * Gets the config for the owner
	 * 
	 * @return The config as type
	 *         org.bukkit.configuration.file.FileConfiguration
	 */
	public FileConfiguration getConfig() {
		if (fc == null) {
			fc = YamlConfiguration.loadConfiguration(getFile());
		}
		return fc;
	}

	/**
	 * Reloads or "Gets" the file and config
	 */
	@SuppressWarnings("deprecation")
	public void reload() {
		if (file == null) {
			file = new File(getDataFolder(), "farmdata.yml");
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fc = YamlConfiguration.loadConfiguration(file);
			InputStream defConfigStream = plugin.getResource("farmdata.yml");
			if (defConfigStream != null) {
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
				fc.setDefaults(defConfig);
			}
		}
	}

	/**
	 * Deletes then creates the config
	 */
	public void resetConfig() {
		delete();
		getConfig();
	}

	/**
	 * Saves the config
	 */
	public void saveConfig() {
		try {
			getConfig().save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
