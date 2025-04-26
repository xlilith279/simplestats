package com.xlilith.simplestats.Ranks.Recolector.Granjero;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerHarvestBlockEvent;

import com.xlilith.simplestats.Main;

public class FoodHarvest implements Listener {
    private final Main plugin;
    private final List<String> worlds;

    public FoodHarvest(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onHarvest(PlayerHarvestBlockEvent e) {
        if (!worlds.contains(e.getPlayer().getWorld().getName())) return;

        UUID id = e.getPlayer().getUniqueId();
        FileConfiguration cfg = plugin.getStatsConfig();
        inc(cfg, "crops_harvested." + id, e.getItemsHarvested().size());
        plugin.saveStats();
    }

    private static void inc(FileConfiguration c, String p, int d) {
        c.set(p, c.getInt(p, 0) + d);
    }
}