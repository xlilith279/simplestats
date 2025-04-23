package com.xlilith.simplestats.Food;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

import java.util.List;
import java.util.UUID;

public class BeetrootEaten implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public BeetrootEaten(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (!worldsAllowed.contains(player.getWorld().getName())) return;

        if (event.getItem().getType() == Material.BEETROOT) {
            UUID uuid = player.getUniqueId();
            FileConfiguration stats = ((Main) plugin).getStatsConfig();
            String path = "beetroots_eaten." + uuid;
            int currentBeetrootsEaten = stats.getInt(path, 0);
            stats.set(path, currentBeetrootsEaten + 1);
            ((Main) plugin).saveStats();
        }
    }
}
