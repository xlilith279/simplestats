/*
 * 
 *  fred, 25/04/2025
 * 
 */
package com.xlilith.simplestats.Ranks.Actuador.Explorador;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

public class MapDraw implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public MapDraw(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMapInitialize(MapInitializeEvent event) {
        if (!worldsAllowed.contains(event.getMap().getWorld().getName())) return;

        UUID uuid = event.getMap().getViewers().stream().findFirst()
            .map(p -> p.getUniqueId()).orElse(null);
        if (uuid == null) return;

        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "maps_drawn." + uuid;
        stats.set(path, stats.getInt(path, 0) + 1);
        ((Main) plugin).saveStats();
    }
}