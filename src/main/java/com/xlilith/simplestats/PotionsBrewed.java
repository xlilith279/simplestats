package com.xlilith.simplestats;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;

public class PotionsBrewed implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public PotionsBrewed(JavaPlugin plugin) {
        this.plugin = plugin;
        // Lista de mundos permitido definida en config.yml bajo "worlds.worlds_list"
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPotionBrew(BrewEvent event) {
        // Obtener un jugador cercano al Brewing Stand (rango 3 bloques)
        Player brewer = event.getBlock().getWorld()
            .getNearbyEntities(event.getBlock().getLocation(), 3, 3, 3).stream()
            .filter(e -> e instanceof Player)
            .map(e -> (Player) e)
            .findFirst()
            .orElse(null);

        if (brewer == null) return;

        if (!worldsAllowed.contains(brewer.getWorld().getName())) return;

        UUID uuid = brewer.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();

        String path = "player_potions." + uuid;
        int currentPotions = stats.getInt(path, 0);
        stats.set(path, currentPotions + 1);
        ((Main) plugin).saveStats();
    }
}