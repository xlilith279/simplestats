package com.xlilith.simplestats;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerJoinHandler implements Listener {
    private final Main plugin;

    public PlayerJoinHandler(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        FileConfiguration stats = plugin.getStatsConfig();
        
        List<String> enabledWorlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Set<String> enabledWorldsSet = new HashSet<>(enabledWorlds);

        if (enabledWorldsSet.contains(player.getWorld().getName())) {
        	String playerUUIDString = playerUUID.toString();
            if (!stats.contains("player_kills." + playerUUIDString) && !stats.contains("deaths." + playerUUIDString) && !stats.contains("mob_kills." + playerUUIDString) && !stats.contains("animals_bred." + playerUUIDString)) {
                stats.set("player_kills." + playerUUIDString, 0);
                stats.set("deaths." + playerUUIDString, 0);
                stats.set("mob_kills." + playerUUIDString, 0);
                stats.set("animals_bred." + playerUUIDString, 0);
                plugin.saveStats();
            }
        }
    }
}
