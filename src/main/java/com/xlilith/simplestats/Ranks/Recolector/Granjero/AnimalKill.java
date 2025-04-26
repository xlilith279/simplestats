package com.xlilith.simplestats.Ranks.Recolector.Granjero;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.xlilith.simplestats.Main;

public class AnimalKill implements Listener {
    private final Main plugin;
    private final List<String> worlds;

    private static final Set<EntityType> FARM_ANIMALS = Set.of(
        EntityType.COW, EntityType.MOOSHROOM, EntityType.SHEEP,
        EntityType.PIG, EntityType.CHICKEN, EntityType.RABBIT
    );

    public AnimalKill(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        Player killer = e.getEntity().getKiller();   // ← forma clásica
        if (killer == null) return;                  // no asesino o no jugador
        if (!worlds.contains(killer.getWorld().getName())) return;
        if (!FARM_ANIMALS.contains(e.getEntityType())) return;

        UUID id = killer.getUniqueId();
        FileConfiguration cfg = plugin.getStatsConfig();
        String path = "farm_animals_killed." + id;
        cfg.set(path, cfg.getInt(path, 0) + 1);
        plugin.saveStats();
    }
}