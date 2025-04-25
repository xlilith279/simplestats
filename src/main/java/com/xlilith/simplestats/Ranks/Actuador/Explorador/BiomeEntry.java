/*
 * 
 *  fred, 25/04/2025
 * 
 */
package com.xlilith.simplestats.Ranks.Actuador.Explorador;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

public class BiomeEntry implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public BiomeEntry(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!worldsAllowed.contains(player.getWorld().getName())) return;
        Biome from = event.getFrom().getBlock().getBiome();
        Biome to   = event.getTo().getBlock().getBiome();
        if (from == to) return;

        UUID uuid = player.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "biome_entries." + uuid + "." + to.name();
        stats.set(path, stats.getInt(path, 0) + 1);
        ((Main) plugin).saveStats();
    }
}