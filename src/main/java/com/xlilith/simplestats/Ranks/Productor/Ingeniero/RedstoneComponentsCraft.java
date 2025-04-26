package com.xlilith.simplestats.Ranks.Productor.Ingeniero;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import com.xlilith.simplestats.Main;

public class RedstoneComponentsCraft implements Listener {
    private final Main plugin;
    private final List<String> worlds;
    private static final Set<Material> COMPONENTS = Set.of(
        Material.REPEATER, Material.COMPARATOR, Material.REDSTONE_TORCH
    );
    public RedstoneComponentsCraft(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onCraft(CraftItemEvent e) {
        if (!COMPONENTS.contains(e.getRecipe().getResult().getType())) return;
        if (!worlds.contains(e.getWhoClicked().getWorld().getName())) return;
        UUID uuid = e.getWhoClicked().getUniqueId();
        FileConfiguration stats = plugin.getStatsConfig();
        String path = "redstone_components_crafted." + uuid;
        stats.set(path, stats.getInt(path, 0) + e.getRecipe().getResult().getAmount());
        plugin.saveStats();
    }
}