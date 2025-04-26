/*
 *  CatchStats – Listener único para peces, tesoros y basura.
 *  fred, 27/04/2025
 */
package com.xlilith.simplestats.Ranks.Recolector.Pescador;

import java.util.*;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import com.xlilith.simplestats.Main;

public class CatchStats implements Listener {
    private final Main plugin;
    private final List<String> worlds;

    /* Conjuntos de clasificación */
    private static final Set<EntityType> FISH_ENTITIES = Set.of(
        EntityType.COD, EntityType.SALMON,
        EntityType.TROPICAL_FISH, EntityType.PUFFERFISH
    );
    private static final Set<Material> TREASURE_ITEMS = Set.of(
        Material.BOW, Material.ENCHANTED_BOOK, Material.NAME_TAG,
        Material.NAUTILUS_SHELL, Material.SADDLE
    );
    private static final Set<Material> JUNK_ITEMS = Set.of(
        Material.ROTTEN_FLESH, Material.STRING, Material.BONE,
        Material.LEATHER_BOOTS, Material.POTION
    );

    public CatchStats(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        if (e.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;
        if (!worlds.contains(e.getPlayer().getWorld().getName())) return;

        Entity caught = e.getCaught();
        if (caught == null) return;

        UUID uuid = e.getPlayer().getUniqueId();
        FileConfiguration cfg = plugin.getStatsConfig();

        // Clasificación
        if (FISH_ENTITIES.contains(caught.getType())) {
            inc(cfg, "fish_caught." + uuid);
        } else if (caught.getType() == EntityType.ITEM) {
            Material m = ((org.bukkit.entity.Item) caught).getItemStack().getType();
            if (TREASURE_ITEMS.contains(m)) {
                inc(cfg, "treasure_caught." + uuid);
            } else if (JUNK_ITEMS.contains(m)) {
                inc(cfg, "junk_caught." + uuid);
            }
        }
        plugin.saveStats();
    }

    /* helper */
    private static void inc(FileConfiguration cfg, String path) {
        cfg.set(path, cfg.getInt(path, 0) + 1);
    }
}