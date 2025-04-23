package com.xlilith.simplestats.Farming;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

import java.util.List;
import java.util.UUID;

public class WheatHarvested implements Listener { // CAMBIAR
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public WheatHarvested(JavaPlugin plugin) { // CAMBIAR
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!worldsAllowed.contains(player.getWorld().getName())) return;

        if (event.getItemInHand().getType() == Material.WHEAT_SEEDS) { // CAMBIAR
            UUID uuid = player.getUniqueId();
            String path = "wheat_planted." + uuid; // CAMBIAR
            int currentWheatPlanted = ((Main) plugin).getStatsConfig().getInt(path, 0); // CAMBIAR
            ((Main) plugin).getStatsConfig().set(path, currentWheatPlanted + 1); // CAMBIAR
            ((Main) plugin).saveStats();
        }
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!worldsAllowed.contains(player.getWorld().getName())) return;

        Block block = event.getBlock();
        if (block.getType() == Material.WHEAT && block.getData() >= 7) { // CAMBIAR
            UUID uuid = player.getUniqueId();
            String path = "wheat_harvested." + uuid; // CAMBIAR
            int currentWheatHarvested = ((Main) plugin).getStatsConfig().getInt(path, 0); // CAMBIAR
            ((Main) plugin).getStatsConfig().set(path, currentWheatHarvested + 1);// CAMBIAR
            ((Main) plugin).saveStats();
        }
    }
}
