/*
 * 
 *  fred, 26/04/2025
 * 
 */
package com.xlilith.simplestats.Ranks.Productor.Cocinero;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

import com.xlilith.simplestats.Main;

public class SuspiciousStewCraft implements Listener {
    private final Main plugin;
    private final List<String> worlds;

    public SuspiciousStewCraft(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        if (!worlds.contains(e.getWhoClicked().getWorld().getName())) return;
        ItemStack result = e.getRecipe().getResult();
        if (result.getType() != Material.SUSPICIOUS_STEW) return;

        UUID uuid = e.getWhoClicked().getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "stews_crafted." + uuid;
        stats.set(path, stats.getInt(path, 0) + 1);
        plugin.saveStats();
    }
}