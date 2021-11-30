package ezieffects.commands;


import ezieffects.Files;
import ezieffects.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Commands implements CommandExecutor {
	public static String arg_null;
	public static boolean requested_item;
	public static ItemStack wand;
	
	String console_use_message = Main.plugin.getConfig().getString("consoleUse");
	String help_message = Main.plugin.getConfig().getString("helpMessage");	
	String itemsMessage = Main.plugin.getConfig().getString("itemsMessage");
	String item_listing_color = Main.plugin.getConfig().getString("itemListingColor");
	String configuration_error_msg = Main.plugin.getConfig().getString("configurationError");
	String item_error_msg = Main.plugin.getConfig().getString("itemError");
	String permission_error_msg = Main.plugin.getConfig().getString("permissionError");	
	
	List<String> items = Files.items.getStringList("items");
    Integer size = items.size();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(console_use_message);
			return true;
		}
		
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("giveitem")) {
			if(player.hasPermission("giveitem.give")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', help_message));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', itemsMessage.replace("%number_of_items%" , size.toString())));
		                for (String s : items){
		                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', item_listing_color + s));
		                }
		                return true;
				} 
				else {
					if (args.length != 0) {
						arg_null = args[0];
						requested_item = items.contains(arg_null);
						if (requested_item) {
							if (args[0].equalsIgnoreCase(arg_null)) {
								try {
								ItemStack item = new ItemStack(Material.STICK);
								ItemMeta meta = item.getItemMeta();
	
								meta.setDisplayName(Files.items.getString(arg_null + ".name"));
	
								List<String> lore = new ArrayList<>();
	
								for(String s : Files.items.getStringList(arg_null + ".lores")) {
									lore.add(s);
								}
								meta.setLore(lore);
								meta.addEnchant(Enchantment.LUCK, 1, false);
								meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
								item.setItemMeta(meta);
								wand = item;
								player.getInventory().addItem(wand);
								} catch(Exception e) {
									sender.sendMessage(ChatColor.translateAlternateColorCodes('&', configuration_error_msg));
								}
							}
						}
						else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', item_error_msg));
						}
	
	
					}
				}
			}else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', permission_error_msg));
			}

				
		}
		
		return true;
	}
}
