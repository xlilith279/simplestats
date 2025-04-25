/*
 * 
 *  fred, 25/04/2025
 * 
 */
package com.xlilith.simplestats.Ranks.Actuador.Caballero;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

public class ShieldBlockCount implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public ShieldBlockCount(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        // Sólo nos importan daños a jugadores
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();

        // Filtrar por mundo
        if (!worldsAllowed.contains(player.getWorld().getName())) return;

        // Comprobar que el jugador está bloqueando con escudo:
        // 1) Acción de bloqueo activa
        // 2) Escudo en la mano secundaria
        if (!player.isHandRaised()) return;
        ItemStack offhand = player.getInventory().getItem(EquipmentSlot.OFF_HAND);
        if (offhand == null || offhand.getType() != Material.SHIELD) return;

        // Si llegamos aquí, el daño fue bloqueado por un escudo
        UUID uuid = player.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "shield_blocks." + uuid;
        stats.set(path, stats.getInt(path, 0) + 1);
        ((Main) plugin).saveStats();
    }
}