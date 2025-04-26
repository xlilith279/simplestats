package com.xlilith.simplestats.Ranks.Recolector.Minero;

import java.util.*;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;
import com.xlilith.simplestats.Main;

public class BlockMining implements Listener {
    private final Main plugin;
    private final List<String> worlds;

    private static final Set<Material> STONES = Set.of(
        Material.STONE, Material.GRANITE, Material.DIORITE, Material.ANDESITE,
        Material.DEEPSLATE, Material.TUFF, Material.CALCITE, Material.END_STONE,
        Material.BLACKSTONE
    );

    private static final Set<Material> ORES = Set.of(
        Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE,
        Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE,
        Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE,
        Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE,
        Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE,
        Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE,
        Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE,
        Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE,
        Material.NETHER_QUARTZ_ORE, Material.NETHER_GOLD_ORE,
        Material.ANCIENT_DEBRIS
    );

    public BlockMining(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (!worlds.contains(e.getBlock().getWorld().getName())) return;

        Material m = e.getBlock().getType();
        UUID id = e.getPlayer().getUniqueId();
        FileConfiguration cfg = plugin.getStatsConfig();

        if (STONES.contains(m))          inc(cfg, "stone_mined."   + id);
        else if (ORES.contains(m))       inc(cfg, "ore_mined."     + id);

        plugin.saveStats();
    }

    private static void inc(FileConfiguration c, String p) {
        c.set(p, c.getInt(p, 0) + 1);
    }
}