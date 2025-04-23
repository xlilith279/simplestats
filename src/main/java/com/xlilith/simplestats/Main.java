package com.xlilith.simplestats;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Tools.*;
import com.xlilith.simplestats.Food.*;
import com.xlilith.simplestats.Farming.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
    private File statsFile;
    private FileConfiguration statsConfig;
    private TopPlayerKills topPlayerKills;

    @Override
    public void onEnable() {
        setupStatsFile();
        saveDefaultConfig();
        this.topPlayerKills = new TopPlayerKills(this);
        new PlayerKills(this);
        new MobKills(this);
        new PlayerDeaths(this);
        new AnimalsBred(this);
        new PotionsBrewed(this);
        new SwordsUsed(this);
        new PickaxesUsed(this);
        new AxesUsed(this);
        new ShovelsUsed(this);
        new HoesUsed(this);
        new AppleEaten(this);
        new BakedPotatoEaten(this);
        new BeetrootEaten(this);
        new BeetrootSoupEaten(this);
        new BreadEaten(this);
        new CarrotEaten(this);
        new ChickenEaten(this);
        new CookedChickenEaten(this);
        new CookedCodEaten(this);
        new CookedMuttonEaten(this);
        new CookedPorkchopEaten(this);
        new CookedRabbitEaten(this);
        new CookedSalmonEaten(this);
        new GoldenAppleEaten(this);
        new MushroomStewEaten(this);
        new PoisonousPotatoEaten(this);
        new PotatoEaten(this);
        new PufferfishEaten(this);
        new RabbitStewEaten(this);
        new RottenFleshEaten(this);
        new SpiderEyeEaten(this);
        new SuspiciousStewEaten(this);
        new MelonSliceEaten(this);
        new SweetBerriesEaten(this);
        new GlowBerriesEaten(this);
        new ChorusFruitEaten(this);
        new DriedKelpEaten(this);
        new BeefEaten(this);
        new CookedBeefEaten(this);
        new PorkchopEaten(this);
        new MuttonEaten(this);
        new EnchantedGoldenAppleEaten(this);
        new PumpkinPieEaten(this);
        new CakeEaten(this);
        new CookieEaten(this);
        new TropicalFishEaten(this);
        new CodEaten(this);
        new RabbitEaten(this);
        new MilkBucketConsumed(this);
        new HoneyBottleConsumed(this);
        new PotionConsumed(this);
        new AcaciaSaplingPlanted(this);
        new BirchSaplingPlanted(this);
        new CherrySaplingPlanted(this);
        new DarkOakSaplingPlanted(this);
        new JungleSaplingPlanted(this);
        new OakSaplingPlanted(this);
        new SpruceSaplingPlanted(this);
        new BambooHarvested(this);
        new BeetrootHarvested(this);
        new CarrotHarvested(this);
        new CocoaBeansHarvested(this);
        new MelonHarvested(this);
        new NetherWartHarvested(this);
        new PotatoHarvested(this);
        new PumpkinHarvested(this);
        new SugarCaneHarvested(this);
        new SweetBerriesHarvested(this);
        new TorchflowerHarvested(this);
        new WheatHarvested(this);
        new PlayerJoinHandler(this);
        StatsPlaceholder statsPlaceholder = new StatsPlaceholder(this, topPlayerKills);
        statsPlaceholder.register();
        getLogger().info("SimpleStats habilitado!");
        getLogger().info("");
        getLogger().info("   _____ _                 _       _____ _        _       ");
        getLogger().info("  / ____(_)               | |     / ____| |      | |      ");
        getLogger().info(" | (___  _ _ __ ___  _ __ | | ___| (___ | |_ __ _| |_ ___ ");
        getLogger().info("  \\___ \\| | '_ ` _ \\| '_ \\| |/ _ \\\\___ \\| __/ _` | __/ __|");
        getLogger().info("  ____) | | | | | | | |_) | |  __/____) | || (_| | |_\\__ \\");
        getLogger().info(" |_____/|_|_| |_| |_| .__/|_|\\___|_____/ \\__\\__,_|\\__|___/");
        getLogger().info("                    | |                                   ");
        getLogger().info("                    |_|                                   ");
        getLogger().info(" Made by: xLilith99_                                     Version: 1.0.1");
        getLogger().info("");
        getLogger().info("Registrando placeholders de SimpleStats...");
    }

    @Override
    public void onDisable() {
        saveStats();
        getLogger().info("SimpleStats deshabilitado!");
    }

    private void setupStatsFile() {
        statsFile = new File(getDataFolder(), "stats.yml");
        if (!statsFile.exists()) {
            saveResource("stats.yml", false);
        }
        statsConfig = YamlConfiguration.loadConfiguration(statsFile);
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("simplestats.reload")) {
            sender.sendMessage("No tienes permiso para ejecutar este comando.");
            return false;
        }
        getLogger().info("Comando recibido: /" + label + " " + String.join(" ", args));
        if (cmd.getName().equalsIgnoreCase("simplestats")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                reloadConfig();
                setupStatsFile();
                StatsPlaceholder statsPlaceholder = new StatsPlaceholder(this, topPlayerKills);
                statsPlaceholder.reload();
                sender.sendMessage("Configuración y placeholders de SimpleStats recargados exitosamente.");
                getLogger().info("Configuración y placeholders de SimpleStats recargados.");
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    public void saveStats() {
        try {
            statsConfig.save(statsFile);
        } catch (IOException e) {
            getLogger().severe("No se ha podido guardar stats.yml!");
        }
    }

    public FileConfiguration getStatsConfig() {
        return statsConfig;
    }
}

class StatsPlaceholder extends PlaceholderExpansion {
    private final Main plugin;
    private final TopPlayerKills topPlayerKills;
    
    public StatsPlaceholder(Main plugin, TopPlayerKills topPlayerKills) {
        this.plugin = plugin;
        this.topPlayerKills = topPlayerKills;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return "simplestats";
    }

    @Override
    public String getAuthor() {
        return "xLilith99_";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }
    
    
    
    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) return "0";

        if (identifier.equals("player_kills")) {
            return String.valueOf(plugin.getStatsConfig().getInt("player_kills." + player.getUniqueId().toString(), 0));
        }
        
        if (identifier.equals("deaths")) {
            return String.valueOf(plugin.getStatsConfig().getInt("deaths." + player.getUniqueId().toString(), 0));
        }
        
        if (identifier.equals("mob_kills")) {
            return String.valueOf(plugin.getStatsConfig().getInt("mob_kills." + player.getUniqueId().toString(), 0));
        }
        
        if (identifier.equals("animals_bred")) {
            return String.valueOf(plugin.getStatsConfig().getInt("animals_bred." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("potions_brewed")) {
            return String.valueOf(plugin.getStatsConfig().getInt("player_potions." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("swords_used")) {
            return String.valueOf(plugin.getStatsConfig().getInt("swords_used." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("pickaxes_used")) {
            return String.valueOf(plugin.getStatsConfig().getInt("pickaxes_used." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("axes_used")) {
            return String.valueOf(plugin.getStatsConfig().getInt("axes_used." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("shovels_used")) {
            return String.valueOf(plugin.getStatsConfig().getInt("shovels_used." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("hoes_used")) {
            return String.valueOf(plugin.getStatsConfig().getInt("hoes_used." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("apples_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("apples_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("baked_potatoes_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("baked_potatoes_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("beetroots_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("beetroots_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("beetroot_soups_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("beetroot_soups_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("bread_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("bread_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("carrots_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("carrots_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("chickens_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("chickens_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cooked_chickens_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cooked_chickens_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cooked_cods_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cooked_cods_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cooked_mutton_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cooked_mutton_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cooked_porkchops_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cooked_porkchops_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cooked_rabbits_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cooked_rabbits_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cooked_salmon_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cooked_salmon_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("golden_apples_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("golden_apples_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("golden_carrots_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("golden_carrots_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("mushroom_stew_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("mushroom_stew_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("poisonous_potatoes_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("poisonous_potatoes_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("potatoes_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("potatoes_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("pufferfish_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("pufferfish_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("rabbit_stew_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("rabbit_stew_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("rotten_flesh_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("rotten_flesh_eaten" + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("spider_eyes_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("spider_eyes_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("suspicious_stew_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("suspicious_stew_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("melon_slices_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("melon_slices_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("sweet_berries_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("sweet_berries_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("glow_berries_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("glow_berries_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("chorus_fruit_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("chorus_fruit_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("dried_kelps_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("dried_kelps_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("beef_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("beef_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cooked_beef_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cooked_beef_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("porkchop_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("porkchop_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("mutton_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("mutton_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("enchanted_golden_apples_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("enchanted_golden_apples_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("pumpkin_pies_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("pumpkin_pies_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cakes_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cakes_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cookies_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cookies_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("tropical_fish_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("tropical_fish_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cods_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cods_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("rabbits_eaten")) {
            return String.valueOf(plugin.getStatsConfig().getInt("rabbits_eaten." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("milk_buckets_consumed")) {
            return String.valueOf(plugin.getStatsConfig().getInt("milk_buckets_consumed." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("honey_bottles_consumed")) {
            return String.valueOf(plugin.getStatsConfig().getInt("honey_bottles_consumed." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("potions_consumed")) {
            return String.valueOf(plugin.getStatsConfig().getInt("potions_consumed." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("acacia_sapling_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("acacia_sapling_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("birch_sapling_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("birch_sapling_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("oak_sapling_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("oak_sapling_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("dark_oak_sapling_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("dark_oak_sapling_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("spruce_sapling_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("spruce_sapling_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("jungle_sapling_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("jungle_sapling_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cherry_sapling_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cherry_sapling_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("wheat_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("wheat_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("wheat_harvested")) {
            return String.valueOf(plugin.getStatsConfig().getInt("wheat_harvested." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("bamboo_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("bamboo_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("bamboo_harvested")) {
            return String.valueOf(plugin.getStatsConfig().getInt("bamboo_harvested." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("beetroot_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("beetroot_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("beetroot_harvested")) {
            return String.valueOf(plugin.getStatsConfig().getInt("beetroot_harvested." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("carrot_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("carrot_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("carrot_harvested")) {
            return String.valueOf(plugin.getStatsConfig().getInt("carrot_harvested." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cocoa_beans_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cocoa_beans_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("cocoa_beans_harvested")) {
            return String.valueOf(plugin.getStatsConfig().getInt("cocoa_beans_harvested." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("melon_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("melon_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("melon_harvested")) {
            return String.valueOf(plugin.getStatsConfig().getInt("melon_harvested." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("nether_wart_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("nether_wart_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("nether_wart_harvested")) {
            return String.valueOf(plugin.getStatsConfig().getInt("nether_wart_harvested." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("potato_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("potato_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("potato_harvested")) {
            return String.valueOf(plugin.getStatsConfig().getInt("potato_harvested." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("pumpkin_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("pumpkin_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("pumpkin_harvested")) {
            return String.valueOf(plugin.getStatsConfig().getInt("pumpkin_harvested." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("sugar_cane_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("sugar_cane_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("sugar_cane_harvested")) {
            return String.valueOf(plugin.getStatsConfig().getInt("sugar_cane_harvested." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("sweet_berries_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("sweet_berries_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("sweet_berries_harvested")) {
            return String.valueOf(plugin.getStatsConfig().getInt("sweet_berries_harvested." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("torchflower_planted")) {
            return String.valueOf(plugin.getStatsConfig().getInt("torchflower_planted." + player.getUniqueId().toString(), 0));
        }

        if (identifier.equals("torchflower_harvested")) {
            return String.valueOf(plugin.getStatsConfig().getInt("torchflower_harvested." + player.getUniqueId().toString(), 0));
        }

        if (identifier.startsWith("topkills_name_") || identifier.startsWith("topkills_kills_")) {
            String[] parts = identifier.split("_");
            if (parts.length != 3) {
                return null;
            }

            int rank;
            try {
                rank = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                return null;
            }

            if (identifier.startsWith("topkills_name_")) {
                return topPlayerKills.getTopKillsInfo(rank, "name");
            } else {
                return topPlayerKills.getTopKillsInfo(rank, "player_kills");
            }
        }

        return null;
    }
    
    public void reload() {
    	
    	this.register();
    }

}