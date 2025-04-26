/*
 * 
 *  fred, 26/04/2025
 * 
 */
package com.xlilith.simplestats.Ranks.Productor.Cocinero;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

import com.xlilith.simplestats.Main;

public class VeggiesCooked implements Listener {
    private final Main plugin;
    private final List<String> worlds;
    private static final Set<Material> RAW_VEG = Set.of(
        Material.POTATO, Material.CARROT, Material.BEETROOT
    );

    public VeggiesCooked(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSmelt(FurnaceSmeltEvent e) {
        if (!worlds.contains(e.getBlock().getWorld().getName())) return;
        if (!RAW_VEG.contains(e.getSource().getType())) return;

        UUID uuid = e.getBlock().getMetadata("smelting_player")
                       .stream().findFirst()
                       .map(m -> (UUID)m.value()).orElse(null);
        if (uuid == null) return;

        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "veggies_cooked." + uuid;
        stats.set(path, stats.getInt(path, 0) + 1);
        plugin.saveStats();
    }
}