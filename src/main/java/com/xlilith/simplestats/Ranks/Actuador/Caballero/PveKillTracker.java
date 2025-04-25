/*
 * 
 *  fred, 25/04/2025
 * 
 */
package com.xlilith.simplestats.Ranks.Actuador.Caballero;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

public class PveKillTracker implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    // Define grupos de tipos
    private static final Set<EntityType> UNDEAD = Set.of(
        EntityType.ZOMBIE, EntityType.SKELETON, EntityType.WITHER_SKELETON,
        EntityType.HUSK, EntityType.DROWNED, EntityType.WITHER_SKELETON,
        EntityType.PHANTOM, EntityType.ZOGLIN, EntityType.ZOMBIE_VILLAGER,
        EntityType.STRAY, EntityType.BOGGED
    );
    private static final Set<EntityType> ARTHROPODS = Set.of(
        EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.SILVERFISH,
        EntityType.ENDERMITE
    );
    private static final Set<EntityType> PILLAGERS = Set.of(
        EntityType.EVOKER, EntityType.PILLAGER, EntityType.VINDICATOR,
        EntityType.ILLUSIONER, EntityType.VEX, EntityType.RAVAGER
    );

    public PveKillTracker(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof LivingEntity)) return;

        Player attacker = (Player) event.getDamager();
        LivingEntity target = (LivingEntity) event.getEntity();

        if (!worldsAllowed.contains(attacker.getWorld().getName())) return;

        // Solo contamos si el golpe mata
        if (event.getFinalDamage() < target.getHealth()) return;

        UUID uuid = attacker.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String category;

        EntityType type = target.getType();
        if (UNDEAD.contains(type)) {
            category = "undead_kills";
        } else if (ARTHROPODS.contains(type)) {
            category = "arthropod_kills";
        } else if (PILLAGERS.contains(type)) {
            category = "pillagers_kills";
        } else {
            return; // no nos interesa otro tipo
        }

        String path = category + "." + uuid;
        stats.set(path, stats.getInt(path, 0) + 1);
        ((Main) plugin).saveStats();
    }
}