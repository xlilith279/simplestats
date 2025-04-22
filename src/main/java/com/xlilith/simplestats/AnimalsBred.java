package com.xlilith.simplestats;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class AnimalsBred implements Listener {
	private final Main plugin;
    private final List<String> worldsAllowed;
	
	public AnimalsBred(Main plugin) {
		this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onEntityBreed(EntityBreedEvent event) {
		if (event.getBreeder() != null && event.getBreeder() instanceof Player) {
			Player player = (Player) event.getBreeder();
            UUID playerUUID = player.getUniqueId();
            FileConfiguration stats = plugin.getStatsConfig();
            String playerUUIDString = playerUUID.toString();
			
    		String worldName = player.getWorld().getName();
    		if (!worldsAllowed.contains(worldName)) return;
    		
    		if (stats.contains("animals_bred." + playerUUIDString)) {
    			int currentBred = stats.getInt("animals_bred." + playerUUIDString);
    			stats.set("animals_bred." + playerUUIDString, currentBred + 1);
    		}
			plugin.saveStats();
		}
	}
}