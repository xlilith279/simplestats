package com.xlilith.simplestats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;

public class AnimalsBred implements Listener {
    private final JavaPlugin plugin;
    private final List<String> worldsAllowed;

    public AnimalsBred(JavaPlugin plugin) {
        this.plugin = plugin;
        this.worldsAllowed = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityBreed(EntityBreedEvent event) {
        if (event.getBreeder() instanceof Player) {
            Player player = (Player) event.getBreeder();
            if (!worldsAllowed.contains(player.getWorld().getName())) return;

            UUID uuid = player.getUniqueId();
            String path = "animals_bred." + uuid;
            int currentAnimalsBred = ((Main) plugin).getStatsConfig().getInt(path, 0);
            ((Main) plugin).getStatsConfig().set(path, currentAnimalsBred + 1);
            ((Main) plugin).saveStats();
        }
    }
}
