package me.scottstorey.manhunt.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Compass {

    public static ItemStack compass() {
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(ChatColor.DARK_PURPLE + "Tracker Compass");
        item.setItemMeta(itemmeta);

        return item;
    }

}
