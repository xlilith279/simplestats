/*
 * 
 *  fred, 25/04/2025
 * 
 */

package com.xlilith.simplestats.Ranks.Actuador.Asesino;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

public class SwordKill implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public SwordKill(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();
        if (killer == null) return;
        if (!worldsAllowed.contains(killer.getWorld().getName())) return;

        ItemStack weapon = killer.getInventory().getItemInMainHand();
        if (!weapon.getType().name().endsWith("_SWORD")) return;

        UUID uuid = killer.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "sword_kills." + uuid.toString();
        int current = stats.getInt(path, 0);
        stats.set(path, current + 1);
        ((Main) plugin).saveStats();
    }
}