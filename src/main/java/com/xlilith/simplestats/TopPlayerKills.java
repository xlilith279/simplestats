package com.xlilith.simplestats;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class TopPlayerKills {
    private final Main plugin;

    public TopPlayerKills(Main plugin) {
        this.plugin = plugin;
    }

    public String getTopKillsInfo(int rank, String type) {
        List<Map.Entry<UUID, Integer>> sortedKills = getSortedKills();

        if (rank <= 0 || rank > sortedKills.size()) {
            return "N/A";
        }

        UUID playerUUID = sortedKills.get(rank - 1).getKey();
        int kills = sortedKills.get(rank - 1).getValue();

        if (type.equalsIgnoreCase("name")) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
            return offlinePlayer.getName() != null ? offlinePlayer.getName() : "Unknown";
        } else if (type.equalsIgnoreCase("player_kills")) {
            return String.valueOf(kills);
        }

        return "...";
    }

    private List<Map.Entry<UUID, Integer>> getSortedKills() {
        FileConfiguration stats = plugin.getStatsConfig();
        Map<UUID, Integer> killsMap = new HashMap<>();

        if (stats.contains("player_kills")) {
            for (String key : stats.getConfigurationSection("player_kills").getKeys(false)) {
                try {
                    UUID uuid = UUID.fromString(key);
                    int kills = stats.getInt("player_kills." + key, 0);
                    killsMap.put(uuid, kills);
                } catch (IllegalArgumentException e) {
                    plugin.getLogger().warning("Clave inválida en player_kills: " + key);
                }
            }
        }

        List<Map.Entry<UUID, Integer>> sortedList = new ArrayList<>(killsMap.entrySet());
        sortedList.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        return sortedList;
    }
}
