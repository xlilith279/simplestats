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
 import org.bukkit.entity.Projectile;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.entity.EntityDamageByEntityEvent;
 import org.bukkit.plugin.java.JavaPlugin;
 
 import com.xlilith.simplestats.Main;
 
 public class ProjectileKill implements Listener {
     private final JavaPlugin plugin;
     private final List<String> worldsAllowed;
 
     public ProjectileKill(JavaPlugin plugin) {
         this.plugin = plugin;
         this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
         Bukkit.getPluginManager().registerEvents(this, plugin);
     }
 
     @EventHandler
     public void onEntityDamage(EntityDamageByEntityEvent event) {
         if (!(event.getDamager() instanceof Projectile)) return;
         Projectile proj = (Projectile) event.getDamager();
         if (!(proj.getShooter() instanceof Player)) return;
         if (!(event.getEntity() instanceof Player)) return;
 
         Player shooter = (Player) proj.getShooter();
         Player victim  = (Player) event.getEntity();
         if (!worldsAllowed.contains(shooter.getWorld().getName())) return;
 
         // Comprobamos si la flecha mata
         if (event.getFinalDamage() < victim.getHealth()) return;
 
         UUID uuid = shooter.getUniqueId();
         FileConfiguration stats = ((Main) plugin).getStatsConfig();
         String path = "projectile_kills." + uuid.toString();
         int current = stats.getInt(path, 0);
         stats.set(path, current + 1);
         ((Main) plugin).saveStats();
     }
} 