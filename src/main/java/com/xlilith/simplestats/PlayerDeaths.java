package com.xlilith.simplestats;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class PlayerDeaths implements Listener {
    private final Main plugin;
    private final List<String> worldsAllowed;

    public PlayerDeaths(Main plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        UUID playerUUID = player.getUniqueId();
        String worldName = player.getWorld().getName();
        FileConfiguration stats = plugin.getStatsConfig();

        if (!worldsAllowed.contains(worldName)) return;
        
        int currentDeaths = stats.getInt("deaths." + playerUUID, 0);
        stats.set("deaths." + playerUUID, currentDeaths + 1);
        plugin.saveStats();
    }
}