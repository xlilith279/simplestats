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

public class RedstoneActuatorsCraft implements Listener {
    private final Main plugin;
    private final List<String> worlds;
    private static final Set<Material> MACHINES = Set.of(
        Material.PISTON, Material.STICKY_PISTON,
        Material.OBSERVER, Material.DROPPER, Material.DISPENSER
    );
    public RedstoneActuatorsCraft(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onCraft(CraftItemEvent e) {
        if (!MACHINES.contains(e.getRecipe().getResult().getType())) return;
        if (!worlds.contains(e.getWhoClicked().getWorld().getName())) return;
        UUID uuid = e.getWhoClicked().getUniqueId();
        FileConfiguration stats = plugin.getStatsConfig();
        String path = "machines_crafted." + uuid;
        stats.set(path, stats.getInt(path, 0) + e.getRecipe().getResult().getAmount());
        plugin.saveStats();
    }
}
