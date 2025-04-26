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

public class PlanksCraft implements Listener {
    private final Main plugin;
    private final List<String> worlds;
    private static final Set<Material> PLANKS = Set.of(
        Material.OAK_PLANKS, Material.SPRUCE_PLANKS, Material.BIRCH_PLANKS,
        Material.JUNGLE_PLANKS, Material.ACACIA_PLANKS, Material.DARK_OAK_PLANKS,
        Material.MANGROVE_PLANKS, Material.CHERRY_PLANKS, Material.WARPED_PLANKS,
        Material.CRIMSON_PLANKS, Material.BAMBOO_PLANKS
    );
    public PlanksCraft(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onCraft(CraftItemEvent e) {
        Material m = e.getRecipe().getResult().getType();
        if (!PLANKS.contains(m)) return;
        if (!worlds.contains(e.getWhoClicked().getWorld().getName())) return;
        UUID uuid = e.getWhoClicked().getUniqueId();
        FileConfiguration stats = plugin.getStatsConfig();
        String path = "planks_crafted." + uuid;
        stats.set(path, stats.getInt(path, 0) + e.getRecipe().getResult().getAmount());
        plugin.saveStats();
    }
}