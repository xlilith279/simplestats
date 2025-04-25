/*
 * 
 *  fred, 25/04/2025
 * 
 */


 package com.xlilith.simplestats.Ranks.Actuador.Asesino;

 import java.util.UUID;
 import java.util.List;
 
 import org.bukkit.Bukkit;
 import org.bukkit.configuration.file.FileConfiguration;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

import io.papermc.paper.event.player.PlayerShieldDisableEvent;
 
 public class ShieldDisabled implements Listener {
     private final JavaPlugin plugin;
     private final List<String> worldsAllowed;
 
     public ShieldDisabled(JavaPlugin plugin) {
         this.plugin = plugin;
         // Carga la lista de mundos permitidos desde config.yml
         this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
         // Registra este listener
         Bukkit.getPluginManager().registerEvents(this, plugin);
     }
 
     @EventHandler
     public void onShieldDisable(PlayerShieldDisableEvent event) {
         // Solo contamos si el atacante es un jugador
         if (!(event.getDamager() instanceof Player)) {
             return;
         }
         Player attacker = (Player) event.getDamager();
 
         // Filtrado por mundo
         if (!worldsAllowed.contains(attacker.getWorld().getName())) {
             return;
         }
 
         // Obtenemos el UUID del atacante
         UUID uuid = attacker.getUniqueId();
 
         // Accedemos al archivo de estadísticas
         FileConfiguration stats = ((Main) plugin).getStatsConfig();
 
         // Ruta donde guardamos el contador: "shields_disabled.<UUID>"
         String path = "shields_disabled." + uuid.toString();
         int current = stats.getInt(path, 0);
 
         // Incrementamos y guardamos
         stats.set(path, current + 1);
         ((Main) plugin).saveStats();
     }
 } 