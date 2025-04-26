package com.xlilith.simplestats.Ranks.Recolector.Minero;

import java.util.*;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.*;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import com.xlilith.simplestats.Main;

public class OreSmelt implements Listener {
    private final Main plugin;  private final List<String> worlds;

    // minerales que entregan lingotes o gemas
    private static final Set<Material> RAW_ORES = Set.of(
        Material.RAW_IRON, Material.RAW_COPPER, Material.RAW_GOLD,
        Material.ANCIENT_DEBRIS, Material.NETHER_QUARTZ_ORE // cuarzo se funde directo
    );

    public OreSmelt(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSmelt(FurnaceSmeltEvent e) {
        if (!worlds.contains(e.getBlock().getWorld().getName())) return;
        if (!RAW_ORES.contains(e.getSource().getType())) return;

        UUID uuid = e.getBlock().getMetadata("smelting_player")
                     .stream().findFirst().map(m -> (UUID)m.value()).orElse(null);
        // si no guardas metadata del jugador, omite esto y cuenta global por horno

        if (uuid == null) return;
        FileConfiguration cfg = plugin.getStatsConfig();
        inc(cfg, "ore_smelted." + uuid);
        plugin.saveStats();
    }

    private static void inc(FileConfiguration c, String p) {
        c.set(p, c.getInt(p, 0) + 1);
    }
}