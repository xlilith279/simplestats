package com.xlilith.simplestats;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerJoinHandler implements Listener {
    private final Main plugin;

    public PlayerJoinHandler(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        FileConfiguration stats = plugin.getStatsConfig();
        
        List<String> enabledWorlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Set<String> enabledWorldsSet = new HashSet<>(enabledWorlds);

        if (enabledWorldsSet.contains(player.getWorld().getName())) {
        	String playerUUIDString = playerUUID.toString();
                    
            if (!stats.contains("player_kills." + playerUUIDString)) {
                stats.set("player_kills." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("deaths." + playerUUIDString)) {
                stats.set("deaths." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("mob_kills." + playerUUIDString)) {
                stats.set("mob_kills." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("animals_bred." + playerUUIDString)) {
                stats.set("animals_bred." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("potions_brewed." + playerUUIDString)) {
                stats.set("potions_brewed." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("swords_used." + playerUUIDString)) {
                stats.set("swords_used." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("pickaxes_used." + playerUUIDString)) {
                stats.set("pickaxes_used." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("axes_used." + playerUUIDString)) {
                stats.set("axes_used." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("hoes_used." + playerUUIDString)) {
                stats.set("hoes_used." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("apples_eaten." + playerUUIDString)) {
                stats.set("apples_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("baked_potatoes_eaten." + playerUUIDString)) {
                stats.set("baked_potatoes_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("beetroots_eaten." + playerUUIDString)) {
                stats.set("beetroots_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("beetroot_soups_eaten." + playerUUIDString)) {
                stats.set("beetroot_soups_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("bread_eaten." + playerUUIDString)) {
                stats.set("bread_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("carrots_eaten." + playerUUIDString)) {
                stats.set("carrots_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("chickens_eaten." + playerUUIDString)) {
                stats.set("chickens_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cooked_chickens_eaten." + playerUUIDString)) {
                stats.set("cooked_chickens_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cooked_cods_eaten." + playerUUIDString)) {
                stats.set("cooked_cods_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cooked_mutton_eaten." + playerUUIDString)) {
                stats.set("cooked_mutton_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cooked_porkchops_eaten." + playerUUIDString)) {
                stats.set("cooked_porkchops_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cooked_rabbits_eaten." + playerUUIDString)) {
                stats.set("cooked_rabbits_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cooked_salmon_eaten." + playerUUIDString)) {
                stats.set("cooked_salmon_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("golden_apples_eaten." + playerUUIDString)) {
                stats.set("golden_apples_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("golden_carrots_eaten." + playerUUIDString)) {
                stats.set("golden_carrots_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("mushroom_stew_eaten." + playerUUIDString)) {
                stats.set("mushroom_stew_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("poisonous_potatoes_eaten." + playerUUIDString)) {
                stats.set("poisonous_potatoes_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("potatoes_eaten." + playerUUIDString)) {
                stats.set("potatoes_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("pufferfish_eaten." + playerUUIDString)) {
                stats.set("pufferfish_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("rabbit_stew_eaten." + playerUUIDString)) {
                stats.set("rabbit_stew_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("rotten_flesh_eaten." + playerUUIDString)) {
                stats.set("rotten_flesh_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("spider_eyes_eaten." + playerUUIDString)) {
                stats.set("spider_eyes_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("suspicious_stew_eaten." + playerUUIDString)) {
                stats.set("suspicious_stew_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("melon_slices_eaten." + playerUUIDString)) {
                stats.set("melon_slices_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("sweet_berries_eaten." + playerUUIDString)) {
                stats.set("sweet_berries_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("glow_berries_eaten." + playerUUIDString)) {
                stats.set("glow_berries_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("chorus_fruit_eaten." + playerUUIDString)) {
                stats.set("chorus_fruit_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("dried_kelps_eaten." + playerUUIDString)) {
                stats.set("dried_kelps_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("beef_eaten." + playerUUIDString)) {
                stats.set("beef_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cooked_beef_eaten." + playerUUIDString)) {
                stats.set("cooked_beef_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("porkchop_eaten." + playerUUIDString)) {
                stats.set("porkchop_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("mutton_eaten." + playerUUIDString)) {
                stats.set("mutton_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("enchanted_golden_apples_eaten." + playerUUIDString)) {
                stats.set("enchanted_golden_apples_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("pumpkin_pies_eaten." + playerUUIDString)) {
                stats.set("pumpkin_pies_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cakes_eaten." + playerUUIDString)) {
                stats.set("cakes_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cookies_eaten." + playerUUIDString)) {
                stats.set("cookies_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("tropical_fish_eaten." + playerUUIDString)) {
                stats.set("tropical_fish_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cods_eaten." + playerUUIDString)) {
                stats.set("cods_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("rabbits_eaten." + playerUUIDString)) {
                stats.set("rabbits_eaten." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("milk_buckets_consumed." + playerUUIDString)) {
                stats.set("milk_buckets_consumed." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("honey_bottles_consumed." + playerUUIDString)) {
                stats.set("honey_bottles_consumed." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("potions_consumed." + playerUUIDString)) {
                stats.set("potions_consumed." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("acacia_sapling_planted." + playerUUIDString)) {
                stats.set("acacia_sapling_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("birch_sapling_planted." + playerUUIDString)) {
                stats.set("birch_sapling_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("oak_sapling_planted." + playerUUIDString)) {
                stats.set("oak_sapling_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("dark_oak_sapling_planted." + playerUUIDString)) {
                stats.set("dark_oak_sapling_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("spruce_sapling_planted." + playerUUIDString)) {
                stats.set("spruce_sapling_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("jungle_sapling_planted." + playerUUIDString)) {
                stats.set("jungle_sapling_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cherry_sapling_planted." + playerUUIDString)) {
                stats.set("cherry_sapling_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("wheat_planted." + playerUUIDString)) {
                stats.set("wheat_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("wheat_harvested." + playerUUIDString)) {
                stats.set("wheat_harvested." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("bamboo_planted." + playerUUIDString)) {
                stats.set("bamboo_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("bamboo_harvested." + playerUUIDString)) {
                stats.set("bamboo_harvested." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("beetroot_planted." + playerUUIDString)) {
                stats.set("beetroot_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("beetroot_harvested." + playerUUIDString)) {
                stats.set("beetroot_harvested." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("carrot_planted." + playerUUIDString)) {
                stats.set("carrot_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("carrot_harvested." + playerUUIDString)) {
                stats.set("carrot_harvested." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cocoa_beans_planted." + playerUUIDString)) {
                stats.set("cocoa_beans_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("cocoa_beans_harvested." + playerUUIDString)) {
                stats.set("cocoa_beans_harvested." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("melon_planted." + playerUUIDString)) {
                stats.set("melon_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("melon_harvested." + playerUUIDString)) {
                stats.set("melon_harvested." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("nether_wart_planted." + playerUUIDString)) {
                stats.set("nether_wart_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("nether_wart_harvested." + playerUUIDString)) {
                stats.set("nether_wart_harvested." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("potato_planted." + playerUUIDString)) {
                stats.set("potato_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("potato_harvested." + playerUUIDString)) {
                stats.set("potato_harvested." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("pumpkin_planted." + playerUUIDString)) {
                stats.set("pumpkin_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("pumpkin_harvested." + playerUUIDString)) {
                stats.set("pumpkin_harvested." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("sugar_cane_planted." + playerUUIDString)) {
                stats.set("sugar_cane_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("sugar_cane_harvested." + playerUUIDString)) {
                stats.set("sugar_cane_harvested." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("sweet_berries_planted." + playerUUIDString)) {
                stats.set("sweet_berries_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("sweet_berries_harvested." + playerUUIDString)) {
                stats.set("sweet_berries_harvested." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("torchflower_planted." + playerUUIDString)) {
                stats.set("torchflower_planted." + playerUUIDString, 0);
                plugin.saveStats();
            }
            
            if (!stats.contains("torchflower_harvested." + playerUUIDString)) {
                stats.set("torchflower_harvested." + playerUUIDString, 0);
                plugin.saveStats();
            }
        }
    }
}