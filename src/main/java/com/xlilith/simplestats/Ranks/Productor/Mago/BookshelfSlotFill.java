package com.xlilith.simplestats.Ranks.Productor.Mago;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import com.xlilith.simplestats.Main;

public class BookshelfSlotFill implements Listener {
    private final Main plugin;
    private final List<String> worlds;
    private static final Set<Material> BOOK_ITEMS = Set.of(
        Material.BOOK, Material.ENCHANTED_BOOK,
        Material.WRITABLE_BOOK, Material.WRITTEN_BOOK,
        Material.KNOWLEDGE_BOOK
    );

    public BookshelfSlotFill(Main plugin) {
        this.plugin = plugin;
        this.worlds = plugin.getConfig().getStringList("worlds.worlds_list");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block b = e.getClickedBlock();
        if (b == null || b.getType() != Material.CHISELED_BOOKSHELF) return;
        if (!worlds.contains(b.getWorld().getName())) return;

        ItemStack inHand = e.getItem();
        if (inHand == null || !BOOK_ITEMS.contains(inHand.getType())) return; // sólo inserción

        UUID uuid = e.getPlayer().getUniqueId();
        FileConfiguration cfg = plugin.getStatsConfig();
        String path = "bookshelf_slots_filled." + uuid;
        cfg.set(path, cfg.getInt(path, 0) + 1);
        plugin.saveStats();
    }
}