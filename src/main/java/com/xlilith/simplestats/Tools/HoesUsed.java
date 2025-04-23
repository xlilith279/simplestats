package com.xlilith.simplestats.Tools;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Main;

import java.util.List;
import java.util.UUID;

public class HoesUsed implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public HoesUsed(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!worldsAllowed.contains(player.getWorld().getName())) return;

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || !isHoe(item.getType())) return;

        UUID uuid = player.getUniqueId();
        FileConfiguration stats = ((Main) plugin).getStatsConfig();
        String path = "hoes_used." + uuid;
        int currentHoesUsed = stats.getInt(path, 0);
        stats.set(path, currentHoesUsed + 1);
        ((Main) plugin).saveStats();
    }

    private boolean isHoe(Material material) {
        switch (material) {
            case WOODEN_HOE:
            case STONE_HOE:
            case GOLDEN_HOE:
            case DIAMOND_HOE:
            case NETHERITE_HOE:
                return true;
            default:
                return false;
        }
    }
}
