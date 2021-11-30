package ezieffects.events;

import ezieffects.commands.Commands;
import ezieffects.Files;

import java.util.List;

import org.bukkit.ChatColor;
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

			Player player = event.getPlayer();
			ItemStack item_in_hand = player.getItemInHand();
			String item_name = item_in_hand.getItemMeta().getDisplayName();
			List<String> item_lore = item_in_hand.getItemMeta().getLore();
			String lore_1 = item_lore.get(0);
			
			String real_item_name;
			
			for(String s : Files.items.getStringList("items")) {
				if ((item_name.equalsIgnoreCase(Files.items.getString(s + ".name"))) & (lore_1.equalsIgnoreCase(Files.items.getStringList(s + ".lores").get(0)))) {
					real_item_name = s;
					effect = Files.items.getString(real_item_name + ".effect").toUpperCase();
					duration = Files.items.getInt(real_item_name + ".duration");
					level = Files.items.getInt(real_item_name + ".level") - 1;
					display_name = Files.items.getString(real_item_name + ".name");

					
					player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect), duration, level));
					event.getItem().setAmount(player.getItemInHand().getAmount()-1);
					
				}
			}

		}
	}
}

