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

public class SugarCaneHarvested implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public SugarCaneHarvested(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!worldsAllowed.contains(player.getWorld().getName())) return;

        if (event.getItemInHand().getType() == Material.SUGAR_CANE) {
            UUID uuid = player.getUniqueId();
            String path = "sugar_cane_planted." + uuid;
            int currentSugarCanePlanted = ((Main) plugin).getStatsConfig().getInt(path, 0);
            ((Main) plugin).getStatsConfig().set(path, currentSugarCanePlanted + 1);
            ((Main) plugin).saveStats();
        }
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!worldsAllowed.contains(player.getWorld().getName())) return;

        Block block = event.getBlock();
        if (block.getType() == Material.SUGAR_CANE && block.getData() >= 7) {
            UUID uuid = player.getUniqueId();
            String path = "sugar_cane_harvested." + uuid;
            int currentSugarCaneHarvested = ((Main) plugin).getStatsConfig().getInt(path, 0);
            ((Main) plugin).getStatsConfig().set(path, currentSugarCaneHarvested + 1);
            ((Main) plugin).saveStats();
        }
    }
}
