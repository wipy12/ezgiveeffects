package ezieffects;

import ezieffects.commands.Commands;
import ezieffects.events.Events;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	
	@Override
	public void onEnable() {
		Files.base(this);
		plugin = this;
		getCommand("giveitem").setExecutor(new Commands());
		getServer().getPluginManager().registerEvents(new Events(), this);
		getLogger().info("EzGiveEffects has enabled");
		saveDefaultConfig();
	}
	@Override
	public void onDisable() {

		getLogger().info("EzGiveEffects has disabled");
	}
}
