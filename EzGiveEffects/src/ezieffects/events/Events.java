package ezieffects.events;

import ezieffects.commands.Commands;
import ezieffects.Files;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events implements Listener{
	public static String effect;
	public static int duration;
	public static int level;
	public static String display_name;
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if (event.getItem() != null) {
			if (Commands.requested_item) {
				effect = Files.items.getString(Commands.arg_null + ".effect").toUpperCase();
				duration = Files.items.getInt(Commands.arg_null + ".duration");
				level = Files.items.getInt(Commands.arg_null + ".level") - 1;
				display_name = Files.items.getString(Commands.arg_null + ".name");

				if (event.getItem().getItemMeta().equals(Commands.wand.getItemMeta())) {
					Player player = event.getPlayer();
					player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect), duration, level));
					event.getItem().setAmount(player.getItemInHand().getAmount()-1);
				}
			}

		}
	}
}

