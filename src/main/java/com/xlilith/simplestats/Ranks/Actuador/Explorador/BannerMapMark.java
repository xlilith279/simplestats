/*
 * 
 *  fred, 25/04/2025
 * 
 */
package com.xlilith.simplestats.Ranks.Actuador.Explorador;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.block.Banner;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

public class BannerMapMark implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public BannerMapMark(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBannerMark(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;
        if (!(event.getClickedBlock() != null && 
              event.getClickedBlock().getState() instanceof Banner)) return;

        Player player = event.getPlayer();
        if (!worldsAllowed.contains(player.getWorld().getName())) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || !item.getType().name().endsWith("_MAP")) return;

        UUID uuid = player.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "banner_map_marks." + uuid;
        stats.set(path, stats.getInt(path, 0) + 1);
        ((Main) plugin).saveStats();
    }
}