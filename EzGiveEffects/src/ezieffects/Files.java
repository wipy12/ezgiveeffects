package ezieffects;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Files {
	public static File configFile;
	public static FileConfiguration config;
	
	public static File itemsFile;
	public static FileConfiguration items;
	
	public static void base(Main main) {
		if (!main.getDataFolder().exists()) {
			main.getDataFolder().mkdirs();
		}
		
		configFile = new File(main.getDataFolder(), "config.yml");
		
		if(!configFile.exists()) {
			main.saveResource("config.yml", false);
		}
		
		config = YamlConfiguration.loadConfiguration(configFile);
		
		itemsFile = new File(main.getDataFolder(), "items.yml");
		
		if(!itemsFile.exists()) {
			main.saveResource("items.yml", false);
		}
		
		items = YamlConfiguration.loadConfiguration(itemsFile);
	}
}
