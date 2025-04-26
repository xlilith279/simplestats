/*
 *  ItemEnchant – gestiona todas las estadísticas de encantamientos.
 *  fred, 26/04/2025
 */
package com.xlilith.simplestats.Ranks.Productor.Mago;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.xlilith.simplestats.Main;

public class ItemEnchant implements Listener {
    private final Main plugin;
    private final List<String> worlds;

    public ItemEnchant(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent e) {
        if (!worlds.contains(e.getEnchanter().getWorld().getName())) return;

        UUID uuid = e.getEnchanter().getUniqueId();
        Material type = e.getItem().getType();
        FileConfiguration stats = plugin.getStatsConfig();

        // 1. contador global y XP gastada
        add(stats, "xp_used_enchant." + uuid, e.getExpLevelCost());

        // 2. categoría específica
        if (isBook(type)) {
            add(stats, "enchants_book." + uuid);
        } else if (isArmor(type)) {
            add(stats, "enchants_armor." + uuid);
        } else if (isWeapon(type)) {
            add(stats, "enchants_weapon." + uuid);
        } else if (isTool(type)) {
            add(stats, "enchants_tool." + uuid);
        }
        plugin.saveStats();
    }

    private static boolean isBook(Material m) {
        return m == Material.BOOK;
    }

    private static boolean isArmor(Material m) {
        EquipmentSlot slot = m.getEquipmentSlot();
        if (slot == null) return false;
        return switch (slot) {
            case HEAD, CHEST, LEGS, FEET -> true;
            default -> m == Material.SHIELD;
        };
    }

    private static boolean isWeapon(Material m) {
        return m.name().endsWith("_SWORD") || m.name().endsWith("_AXE") || m == Material.BOW || m == Material.CROSSBOW ||
               m == Material.TRIDENT || m == Material.MACE;
    }

    private static boolean isTool(Material m) {
        String n = m.name();
        return n.endsWith("_PICKAXE") || n.endsWith("_AXE") || n.endsWith("_SHOVEL") ||
               n.endsWith("_HOE") || m == Material.SHEARS || m == Material.FISHING_ROD;
    }

    private static void add(FileConfiguration cfg, String path) {
        add(cfg, path, 1);
    }

    private static void add(FileConfiguration cfg, String path, int delta) {
        cfg.set(path, cfg.getInt(path, 0) + delta);
    }
}