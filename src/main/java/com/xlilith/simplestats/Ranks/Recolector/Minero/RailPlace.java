/*
 *
 *  fred, 27/04/2025
 */
package com.xlilith.simplestats.Ranks.Recolector.Minero;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.xlilith.simplestats.Main;

public class RailPlace implements Listener {

    private final Main plugin;
    private final List<String> worlds;

    public RailPlace(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (!worlds.contains(e.getBlock().getWorld().getName())) return;

        Material placed = e.getBlockPlaced().getType();
        if (!Tag.RAILS.isTagged(placed)) return;           // ← uso correcto del tag

        UUID id = e.getPlayer().getUniqueId();
        FileConfiguration cfg = plugin.getStatsConfig();
        String path = "rails_placed." + id;
        cfg.set(path, cfg.getInt(path, 0) + 1);
        plugin.saveStats();
    }
}