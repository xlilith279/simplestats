/*
 * 
 *  fred, 25/04/2025
 * 
 */
package com.xlilith.simplestats.Ranks.Actuador.Caballero;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

public class SweepingAttackTracker implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public SweepingAttackTracker(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSweepAttack(EntityDamageByEntityEvent event) {
        if (event.getCause() != DamageCause.ENTITY_SWEEP_ATTACK) return;

        Entity damager = event.getDamager();
        if (!(damager instanceof Player)) return;

        Player player = (Player) damager;
        if (!worldsAllowed.contains(player.getWorld().getName())) return;

        UUID uuid = player.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "sweeping_attacks." + uuid;
        stats.set(path, stats.getInt(path, 0) + 1);
        ((Main) plugin).saveStats();
    }
}