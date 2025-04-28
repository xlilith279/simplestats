package com.xlilith.simplestats.Ranks.Recolector.Pescador;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.MerchantRecipe;

import com.xlilith.simplestats.Main;

import io.papermc.paper.event.player.PlayerTradeEvent;

public class FishermanTrade implements Listener {

    private final Main plugin;
    private final List<String> worlds;

    public FishermanTrade(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFisherTrade(PlayerTradeEvent e) {

        Player player = e.getPlayer();
        if (!worlds.contains(player.getWorld().getName())) return;

        MerchantRecipe recipe = e.getTrade();

        // ¿aparece pescado crudo en algún lado?
        boolean hasRawFish = recipe.getIngredients().stream()
                .anyMatch(i -> i.getType() == Material.COD || i.getType() == Material.SALMON)
            || recipe.getResult().getType() == Material.COD
            || recipe.getResult().getType() == Material.TROPICAL_FISH
            || recipe.getResult().getType() == Material.PUFFERFISH
            || recipe.getResult().getType() == Material.SALMON;

        if (!hasRawFish) return;

        // contar
        UUID id = player.getUniqueId();
        FileConfiguration cfg = plugin.getStatsConfig();
        String path = "fish_for_emerald_trades." + id;
        cfg.set(path, cfg.getInt(path, 0) + 1);
        plugin.saveStats();
    }

}