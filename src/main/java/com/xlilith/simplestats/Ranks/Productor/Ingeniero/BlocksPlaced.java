/*
 *
 *  fred, 26/04/2025
 *
 */
package com.xlilith.simplestats.Ranks.Productor.Ingeniero;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.xlilith.simplestats.Main;

public class BlocksPlaced implements Listener {
    private final Main plugin;
    private final List<String> worlds;

    public BlocksPlaced(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (!worlds.contains(e.getBlock().getWorld().getName())) return;

        UUID uuid = e.getPlayer().getUniqueId();
        Material placed = e.getBlock().getType();
        FileConfiguration stats = plugin.getStatsConfig();

        // 1. Contador general
        add(stats, "constructor_blocks_placed." + uuid);

        // 2. Categoría redstone basada en propiedades físicas
        if (placed.isOccluding()) {
            add(stats, "redstone_solid_blocks_placed." + uuid);
        } else {
            add(stats, "redstone_transparent_blocks_placed." + uuid);
        }

        plugin.saveStats();
    }

    /** Incrementa ruta en +1 */
    private static void add(FileConfiguration stats, String path) {
        stats.set(path, stats.getInt(path, 0) + 1);
    }
}