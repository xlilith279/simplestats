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
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.xlilith.simplestats.Main;

public class MapDraw implements Listener {

    private final Main plugin;
    private final List<String> worldsAllowed;

    public MapDraw(Main plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Cuenta cada vez que un jugador “dibuja” un mapa:
     * hacer clic con un {@link Material#MAP} (mapa vacío) ➜ se convierte en filled_map.
     */
    @EventHandler
    public void onRightClickEmptyMap(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) return;                  // solo mano principal
        if (e.getAction() != Action.RIGHT_CLICK_AIR &&
            e.getAction() != Action.RIGHT_CLICK_BLOCK) return;          // clic derecho
        if (e.getItem() == null || e.getItem().getType() != Material.MAP) return;

        if (!worldsAllowed.contains(e.getPlayer().getWorld().getName())) return;

        UUID uuid = e.getPlayer().getUniqueId();
        FileConfiguration stats = plugin.getStatsConfig();
        String path = "maps_drawn." + uuid;
        stats.set(path, stats.getInt(path, 0) + 1);
        plugin.saveStats();
    }
}