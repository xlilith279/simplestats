/*
 * 
 *  fred, 25/04/2025
 * 
 */
package com.xlilith.simplestats.Ranks.Actuador.Explorador;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.xlilith.simplestats.Main;

public class CompassTimeTracker implements Listener {
    private final List<String> worldsAllowed;
    private final Map<UUID, Long> startTimes = new HashMap<>();

    public CompassTimeTracker(JavaPlugin plugin) {
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);

        // Cada segundo guardamos stats acumuladas
        new BukkitRunnable() {
            @Override
            public void run() {
                FileConfiguration stats = ((Main) plugin).getStatsConfig();
                long now = System.currentTimeMillis();
                for (Map.Entry<UUID, Long> e : startTimes.entrySet()) {
                    UUID uuid = e.getKey();
                    long deltaSec = (now - e.getValue()) / 1000;
                    String path = "compass_time_seconds." + uuid;
                    stats.set(path, stats.getInt(path, 0) + (int) deltaSec);
                    e.setValue(now);
                }
                ((Main) plugin).saveStats();
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (!worldsAllowed.contains(player.getWorld().getName())) return;
        ItemStack newItem = player.getInventory().getItem(event.getNewSlot());
        UUID uuid = player.getUniqueId();
        if (newItem != null && newItem.getType() == Material.COMPASS) {
            startTimes.put(uuid, System.currentTimeMillis());
        } else {
            startTimes.remove(uuid);
        }
    }
}