package com.xlilith.simplestats;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerKills implements Listener {
    private final Main plugin;
    private final List<String> worldsAllowed;
    
    public PlayerKills(Main plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (killer == null) return;

        String worldName = killer.getWorld().getName();
        if (!worldsAllowed.contains(worldName)) return;

        UUID killerUUID = killer.getUniqueId();
        FileConfiguration stats = plugin.getStatsConfig();

        int currentKills = stats.getInt("player_kills." + killerUUID, 0);
        stats.set("player_kills." + killerUUID, currentKills + 1);
        plugin.saveStats();
    }
}
