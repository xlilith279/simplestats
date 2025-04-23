package com.xlilith.simplestats.Tools;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

import java.util.List;
import java.util.UUID;

public class SwordsUsed implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public SwordsUsed(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();
        if (!worldsAllowed.contains(player.getWorld().getName())) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || !isSword(item.getType())) return;

        UUID uuid = player.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "swords_used." + uuid;
        int currentSwordsUsed = stats.getInt(path, 0);
        stats.set(path, currentSwordsUsed + 1);
        ((Main) plugin).saveStats();
    }

    private boolean isSword(Material material) {
        switch (material) {
            case WOODEN_SWORD:
            case STONE_SWORD:
            case GOLDEN_SWORD:
            case DIAMOND_SWORD:
            case NETHERITE_SWORD:
                return true;
            default:
                return false;
        }
    }
}
