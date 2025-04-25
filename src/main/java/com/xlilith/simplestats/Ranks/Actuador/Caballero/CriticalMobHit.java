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
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

public class CriticalMobHit implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public CriticalMobHit(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCriticalHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof LivingEntity)) return;
        if (!event.isCritical()) return;

        Player attacker = (Player) event.getDamager();
        if (!worldsAllowed.contains(attacker.getWorld().getName())) return;

        UUID uuid = attacker.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "mob_critical_hits." + uuid;
        stats.set(path, stats.getInt(path, 0) + 1);
        ((Main) plugin).saveStats();
    }
}