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
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.xlilith.simplestats.Main;

public class ExplosivePotionThrown implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public ExplosivePotionThrown(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;
        Player shooter = (Player) event.getEntity().getShooter();
        if (!worldsAllowed.contains(shooter.getWorld().getName())) return;

        boolean hasDamage = false;
        for (PotionEffect e : event.getPotion().getEffects()) {
            if (e.getType() == PotionEffectType.INSTANT_DAMAGE) {
                hasDamage = true;
                break;
            }
        }
        if (!hasDamage) return;

        UUID uuid = shooter.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "explosive_potions_thrown." + uuid.toString();
        int current = stats.getInt(path, 0);
        stats.set(path, current + 1);
        ((Main) plugin).saveStats();
    }
}