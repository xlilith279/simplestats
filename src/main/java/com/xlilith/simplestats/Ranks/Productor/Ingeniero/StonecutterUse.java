package com.xlilith.simplestats.Ranks.Productor.Ingeniero;

import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import com.xlilith.simplestats.Main;

public class StonecutterUse implements Listener {
    private final Main plugin;
    private final List<String> worlds;
    public StonecutterUse(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onStonecut(InventoryClickEvent e) {
        if (e.getInventory().getType() != InventoryType.STONECUTTER) return;
        if (!worlds.contains(e.getWhoClicked().getWorld().getName())) return;
        // Contamos clic en el slot de resultado (slot 1)
        if (e.getSlot() != 1) return;
        if (!e.isLeftClick() && !e.isRightClick()) return;
        UUID uuid = e.getWhoClicked().getUniqueId();
        FileConfiguration stats = plugin.getStatsConfig();
        String path = "stonecutter_used." + uuid;
        stats.set(path, stats.getInt(path, 0) + 1);
        plugin.saveStats();
    }
}