package com.xlilith.simplestats;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.Player;

import java.util.*;

public class MobKills implements Listener {
    private final Main plugin;
    private final List<String> worldsAllowed;
    
    public MobKills(Main plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
    	if (event.getEntity().getKiller() != null) {
    		Player player = event.getEntity().getKiller();
    		UUID playerUUID = player.getUniqueId();
    		FileConfiguration stats = plugin.getStatsConfig();
    		String playerUUIDString = playerUUID.toString();
    		
    		String worldName = player.getWorld().getName();
    		if (!worldsAllowed.contains(worldName)) return;
    		
    		if (stats.contains("mob_kills." + playerUUIDString)) {
    			int currentKills = stats.getInt("mob_kills." + playerUUIDString);
    			stats.set("mob_kills." + playerUUIDString, currentKills + 1);
    		}
            plugin.saveStats();
    	}
    }
}