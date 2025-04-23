package com.xlilith.simplestats.Tools;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

import java.util.List;
import java.util.UUID;

public class PickaxesUsed implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public PickaxesUsed(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!worldsAllowed.contains(player.getWorld().getName())) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || !isPickaxe(item.getType())) return;

        UUID uuid = player.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "pickaxes_used." + uuid;
        int currentPickaxesUsed = stats.getInt(path, 0);
        stats.set(path, currentPickaxesUsed + 1);
        ((Main) plugin).saveStats();
    }

    private boolean isPickaxe(Material material) {
        switch (material) {
            case WOODEN_PICKAXE:
            case STONE_PICKAXE:
            case GOLDEN_PICKAXE:
            case DIAMOND_PICKAXE:
            case NETHERITE_PICKAXE:
                return true;
            default:
                return false;
        }
    }
}
