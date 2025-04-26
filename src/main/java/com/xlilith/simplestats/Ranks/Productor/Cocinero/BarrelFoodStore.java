/*
 * 
 *  fred, 26/04/2025
 * 
 */
package com.xlilith.simplestats.Ranks.Productor.Cocinero;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.xlilith.simplestats.Main;

public class BarrelFoodStore implements Listener {
    private final Main plugin;
    private final List<String> worlds;
    // Para cada jugador, un mapa material → último timestamp
    private final Map<UUID, Map<Material, Long>> lastStoreTime = new ConcurrentHashMap<>();
    private final long cooldownMs;

    public BarrelFoodStore(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        this.cooldownMs = 3000;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        // Sólo barriles en mundos permitidos
        if (!(e.getInventory().getHolder() instanceof Barrel)) return;
        if (!worlds.contains(e.getWhoClicked().getWorld().getName())) return;

        ItemStack cursor = e.getCursor();
        if (cursor == null || !cursor.getType().isEdible()) return;
        Material mat = cursor.getType();
        UUID uuid = e.getWhoClicked().getUniqueId();

        long now = System.currentTimeMillis();
        // Obtén o crea el mapa de tiempos para este jugador
        Map<Material, Long> playerTimes = 
            lastStoreTime.computeIfAbsent(uuid, k -> new ConcurrentHashMap<>());

        Long last = playerTimes.get(mat);
        if (last != null && now - last < cooldownMs) {
            // Aún en cooldown para este mismo material: no contabilizamos
            return;
        }

        // Pasa el cooldown para este material: contamos y actualizamos timestamp
        playerTimes.put(mat, now);

        FileConfiguration stats = plugin.getStatsConfig();
        String path = "food_in_barrel." + uuid;
        stats.set(path, stats.getInt(path, 0) + 1);
        plugin.saveStats();
    }
}