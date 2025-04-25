/*
 * 
 *  fred, 25/04/2025
 * 
 */
package com.xlilith.simplestats.Ranks.Actuador.Explorador;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

public class InspectSuspiciousBlock implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public InspectSuspiciousBlock(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInspect(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;
        Block b = event.getClickedBlock();
        if (b == null) return;
        Material m = b.getType();
        if (m != Material.SUSPICIOUS_GRAVEL && m != Material.SUSPICIOUS_SAND) return;

        Player p = event.getPlayer();
        if (!worldsAllowed.contains(p.getWorld().getName())) return;

        UUID uuid = p.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "inspections_suspicious." + uuid;
        stats.set(path, stats.getInt(path, 0) + 1);
        ((Main) plugin).saveStats();
    }
}