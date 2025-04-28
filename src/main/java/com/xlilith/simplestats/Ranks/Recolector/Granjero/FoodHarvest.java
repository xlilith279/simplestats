package com.xlilith.simplestats.Ranks.Recolector.Granjero;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;

import com.xlilith.simplestats.Main;

public class FoodHarvest implements Listener {
    private final Main plugin;
    private final List<String> worlds;

    public FoodHarvest(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private static final Set<Material> CROPS = EnumSet.of(
        Material.WHEAT, Material.POTATOES, Material.CARROTS,
        Material.BEETROOTS, Material.NETHER_WART, Material.PITCHER_CROP);

    @EventHandler
    public void onCropBreak(BlockBreakEvent e) {
        if (!worlds.contains(e.getPlayer().getWorld().getName())) return;

        Block b = e.getBlock();
        if (!CROPS.contains(b.getType())) return;                          // filtra cultivos
        BlockData data = b.getBlockData();
        if (!(data instanceof Ageable age) || age.getAge() < age.getMaximumAge()) return;

        UUID id = e.getPlayer().getUniqueId();
        FileConfiguration cfg = plugin.getStatsConfig();
        inc(cfg, "crops_harvested." + id, 1);                              // +1 por cultivo maduro
        plugin.saveStats();
    }

    @EventHandler
    public void onHarvest(PlayerHarvestBlockEvent e) {
        if (!worlds.contains(e.getPlayer().getWorld().getName())) return;

        UUID id = e.getPlayer().getUniqueId();
        FileConfiguration cfg = plugin.getStatsConfig();
        inc(cfg, "crops_harvested." + id, e.getItemsHarvested().size());
        plugin.saveStats();
    }

    private static void inc(FileConfiguration c, String p, int d) {
        c.set(p, c.getInt(p, 0) + d);
    }
}