package com.xlilith.simplestats;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
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
        new PotionsBrewed(this);
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
                

                StatsPlaceholder statsPlaceholder = new StatsPlaceholder(this, topPlayerKills);
                statsPlaceholder.reload();

                sender.sendMessage("Placeholders de SimpleStats recargados exitosamente.");
                getLogger().info("Placeholders de SimpleStats recargados.");
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