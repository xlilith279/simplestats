package com.xlilith.simplestats.Farming;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

import java.util.List;
import java.util.UUID;

public class AcaciaSaplingPlanted implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public AcaciaSaplingPlanted(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!worldsAllowed.contains(player.getWorld().getName())) return;

        if (event.getItemInHand().getType() == Material.ACACIA_SAPLING) {
            UUID uuid = player.getUniqueId();
            String path = "acacia_sapling_planted." + uuid;
            int currentAcaciaSaplingPlanted = ((Main) plugin).getStatsConfig().getInt(path, 0);
            ((Main) plugin).getStatsConfig().set(path, currentAcaciaSaplingPlanted + 1);
            ((Main) plugin).saveStats();
        }
    }
}
