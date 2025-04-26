package com.xlilith.simplestats.Ranks.Recolector.Granjero;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.xlilith.simplestats.Main;

public class CowSheepInteract implements Listener {
    private final Main plugin;
    private final List<String> worlds;

    public CowSheepInteract(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    // ---- Ordeñar vacas ----------------------------------------------------
    @EventHandler
    public void onMilk(PlayerInteractEntityEvent e) {
        if (!(e.getRightClicked() instanceof Cow)) return;
        if (!worlds.contains(e.getPlayer().getWorld().getName())) return;

        ItemStack hand = e.getPlayer().getInventory().getItem(e.getHand());
        if (hand == null || hand.getType() != Material.BUCKET) return; // sólo si lleva cubo

        track("cows_milked.", e.getPlayer().getUniqueId());
    }

    // ---- Esquilar ovejas --------------------------------------------------
    @EventHandler
    public void onShear(PlayerShearEntityEvent e) {
        if (!(e.getEntity() instanceof Sheep)) return;
        if (!worlds.contains(e.getPlayer().getWorld().getName())) return;

        track("sheep_sheared.", e.getPlayer().getUniqueId());
    }

    private void track(String base, UUID id) {
        FileConfiguration cfg = plugin.getStatsConfig();
        cfg.set(base + id, cfg.getInt(base + id, 0) + 1);
        plugin.saveStats();
    }
}