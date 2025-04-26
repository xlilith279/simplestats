package com.xlilith.simplestats.Ranks.Productor.Mago;

import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import com.xlilith.simplestats.Main;

public class PotionConsume implements Listener {
    private final Main plugin;
    private final List<String> worlds;

    public PotionConsume(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDrink(PlayerItemConsumeEvent e) {
        ItemStack it = e.getItem();
        if (it.getType() != Material.POTION) return;
        if (!worlds.contains(e.getPlayer().getWorld().getName())) return;

        track("potions_drank.", e.getPlayer().getUniqueId());
    }

    private void track(String base, UUID uuid) {
        FileConfiguration cfg = plugin.getStatsConfig();
        String path = base + uuid;
        cfg.set(path, cfg.getInt(path, 0) + 1);
        plugin.saveStats();
    }
}