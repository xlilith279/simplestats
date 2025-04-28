package com.xlilith.simplestats.Ranks.Recolector.Granjero;

import java.util.List;
import java.util.UUID;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.xlilith.simplestats.Main;

public class FarmerInteract implements Listener {
    private final Main plugin;
    private final List<String> worlds;

    public FarmerInteract(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (!worlds.contains(e.getPlayer().getWorld().getName())) return;

        ItemStack item = e.getItem();
        Block block = e.getClickedBlock();
        if (item == null) return;

        UUID id = e.getPlayer().getUniqueId();
        FileConfiguration cfg = plugin.getStatsConfig();

        // ---- Polvo de hueso ------------------------------------------------
        if (item.getType() == Material.BONE_MEAL
                && e.getAction() == Action.RIGHT_CLICK_BLOCK
                && block != null) {

            BlockData data = block.getBlockData();
            if (data instanceof Ageable ageable
                    && ageable.getAge() < ageable.getMaximumAge()) {
                inc(cfg, "bone_meal_used." + id);   // solo cuenta si realmente surte efecto
            }
        }

        // ---- Composter -----------------------------------------------------
        if (block != null &&
            block.getType() == Material.COMPOSTER &&
            e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            inc(cfg, "composter_used." + id);
        }

        plugin.saveStats();
    }

    private static void inc(FileConfiguration c, String p) {
        c.set(p, c.getInt(p, 0) + 1);
    }
}