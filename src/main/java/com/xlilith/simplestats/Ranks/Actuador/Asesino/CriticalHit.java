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
 import org.bukkit.event.entity.EntityDamageByEntityEvent;
 import org.bukkit.plugin.java.JavaPlugin;
 
 import com.xlilith.simplestats.Main;
 
 public class CriticalHit implements Listener {
     private final JavaPlugin plugin;
     private final List<String> worldsAllowed;
 
     public CriticalHit(JavaPlugin plugin) {
         this.plugin = plugin;
         this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
         Bukkit.getPluginManager().registerEvents(this, plugin);
     }
 
     @EventHandler
     public void onEntityDamage(EntityDamageByEntityEvent event) {
         if (!(event.getDamager() instanceof Player)) return;
         Player attacker = (Player) event.getDamager();
         if (!worldsAllowed.contains(attacker.getWorld().getName())) return;
 
         // Paper API expone el flag crítico en el evento
         if (!event.isCritical()) return;
 
         UUID uuid = attacker.getUniqueId();
         FileConfiguration stats = ((Main) plugin).getStatsConfig();
         String path = "critical_hits." + uuid.toString();
         int current = stats.getInt(path, 0);
         stats.set(path, current + 1);
         ((Main) plugin).saveStats();
     }
 } 