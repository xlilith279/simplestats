package com.xlilith.simplestats.Ranks.Recolector.Pescador;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.TradeSelectEvent; // Paper 1.20+
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import com.xlilith.simplestats.Main;

public class FishermanTrade implements Listener {

    private final Main plugin;
    private final List<String> worlds;

    public FishermanTrade(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onTrade(TradeSelectEvent e) {

        /* Player and world filter */
        Player player = (Player) e.getWhoClicked();            // correct getter
        if (!worlds.contains(player.getWorld().getName())) return;

        /* Merchant must be a fisherman-villager */
        Merchant merchant = e.getMerchant();                   // correct getter
        if (!(merchant instanceof Villager v)
            || v.getProfession() != Villager.Profession.FISHERMAN) return;

        /* Fetch the selected recipe */
        MerchantRecipe recipe = merchant.getRecipe(e.getIndex());

        /* Result must be emeralds */
        if (recipe.getResult().getType() != Material.EMERALD) return;

        /* At least one ingredient is raw COD or SALMON */
        boolean rawFish =
            recipe.getIngredients().stream()
                  .anyMatch(i -> i.getType() == Material.COD
                              || i.getType() == Material.SALMON);
        if (!rawFish) return;

        /* Count the trade */
        UUID id = player.getUniqueId();
        FileConfiguration cfg = plugin.getStatsConfig();
        String path = "fish_for_emerald_trades." + id;
        cfg.set(path, cfg.getInt(path, 0) + 1);
        plugin.saveStats();
    }
}