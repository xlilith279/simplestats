package com.xlilith.simplestats;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.xlilith.simplestats.Tools.*;
import com.xlilith.simplestats.Food.*;
import com.xlilith.simplestats.Ranks.Productor.Ingeniero.RedstoneComponentsCraft;
import com.xlilith.simplestats.Ranks.Actuador.Asesino.AxeKill;
import com.xlilith.simplestats.Ranks.Actuador.Asesino.CriticalHit;
import com.xlilith.simplestats.Ranks.Actuador.Asesino.DamageToPoisonedPlayer;
import com.xlilith.simplestats.Ranks.Actuador.Asesino.DamageWhileInvisible;
import com.xlilith.simplestats.Ranks.Actuador.Asesino.ExplosivePotionThrown;
import com.xlilith.simplestats.Ranks.Actuador.Asesino.KillWithoutSwordOrAxe;
import com.xlilith.simplestats.Ranks.Actuador.Asesino.ProjectileKill;
import com.xlilith.simplestats.Ranks.Actuador.Asesino.ShieldDisabled;
import com.xlilith.simplestats.Ranks.Actuador.Asesino.SwordKill;
import com.xlilith.simplestats.Ranks.Actuador.Caballero.CriticalMobHit;
import com.xlilith.simplestats.Ranks.Actuador.Caballero.PveKillTracker;
import com.xlilith.simplestats.Ranks.Actuador.Caballero.ShieldBlockCount;
import com.xlilith.simplestats.Ranks.Actuador.Caballero.SweepingAttackTracker;
import com.xlilith.simplestats.Ranks.Actuador.Explorador.BannerMapMark;
import com.xlilith.simplestats.Ranks.Actuador.Explorador.BiomeEntry;
import com.xlilith.simplestats.Ranks.Actuador.Explorador.CompassTimeTracker;
import com.xlilith.simplestats.Ranks.Actuador.Explorador.FoodConsume;
import com.xlilith.simplestats.Ranks.Actuador.Explorador.InspectSuspiciousBlock;
import com.xlilith.simplestats.Ranks.Actuador.Explorador.MapDraw;
import com.xlilith.simplestats.Ranks.Productor.Cocinero.BarrelFoodStore;
import com.xlilith.simplestats.Ranks.Productor.Cocinero.FishCooked;
import com.xlilith.simplestats.Ranks.Productor.Cocinero.MeatCooked;
import com.xlilith.simplestats.Ranks.Productor.Cocinero.SuspiciousStewCraft;
import com.xlilith.simplestats.Ranks.Productor.Cocinero.VeggiesCooked;
import com.xlilith.simplestats.Ranks.Productor.Ingeniero.BlocksPlaced;
import com.xlilith.simplestats.Ranks.Productor.Ingeniero.PlanksCraft;
import com.xlilith.simplestats.Ranks.Productor.Ingeniero.RedstoneActuatorsCraft;
import com.xlilith.simplestats.Ranks.Productor.Ingeniero.StonecutterUse;
import com.xlilith.simplestats.Ranks.Productor.Mago.BookshelfSlotFill;
import com.xlilith.simplestats.Ranks.Productor.Mago.ItemEnchant;
import com.xlilith.simplestats.Ranks.Productor.Mago.PotionConsume;
import com.xlilith.simplestats.Ranks.Productor.Mago.PotionsBrewed;
import com.xlilith.simplestats.Ranks.Recolector.Granjero.AnimalKill;
import com.xlilith.simplestats.Ranks.Recolector.Granjero.CowSheepInteract;
import com.xlilith.simplestats.Ranks.Recolector.Granjero.FarmerInteract;
import com.xlilith.simplestats.Ranks.Recolector.Granjero.FoodHarvest;
import com.xlilith.simplestats.Ranks.Recolector.Minero.BlockMining;
import com.xlilith.simplestats.Ranks.Recolector.Minero.OreSmelt;
import com.xlilith.simplestats.Ranks.Recolector.Minero.RailPlace;
import com.xlilith.simplestats.Ranks.Recolector.Pescador.CatchStats;
import com.xlilith.simplestats.Ranks.Recolector.Pescador.FishermanTrade;
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

        // -- RANKS --

        // Asesino
        new ShieldDisabled(this);
        new AxeKill(this);
        new CriticalHit(this);
        new DamageToPoisonedPlayer(this);
        new DamageWhileInvisible(this);
        new ExplosivePotionThrown(this);
        new KillWithoutSwordOrAxe(this);
        new ProjectileKill(this);
        new SwordKill(this);

        // Explorador
        new BannerMapMark(this);
        new BiomeEntry(this);
        new CompassTimeTracker(this);
        new FoodConsume(this);
        new InspectSuspiciousBlock(this);
        new MapDraw(this);
    
        // Caballero
        new CriticalMobHit(this);
        new PveKillTracker(this);
        new ShieldBlockCount(this);
        new SweepingAttackTracker(this);

        // Cocinero
        new BarrelFoodStore(this);
        new FishCooked(this);
        new MeatCooked(this);
        new SuspiciousStewCraft(this);
        new VeggiesCooked(this);

        // Ingeniero
        new PlanksCraft(this);
        new StonecutterUse(this);
        new RedstoneComponentsCraft(this);
        new RedstoneActuatorsCraft(this);
        new BlocksPlaced(this);

        // Mago
        new PotionsBrewed(this);
        new PotionConsume(this);
        new BookshelfSlotFill(this);
        new ItemEnchant(this);

        // Pescador
        new CatchStats(this);
        new FishermanTrade(this);

        // Granjero
        new AnimalKill(this);
        new CowSheepInteract(this);
        new FarmerInteract(this);
        new FoodHarvest(this);

        // Minero
        new BlockMining(this);
        new OreSmelt(this);
        new RailPlace(this);

        // -----------

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

        StatType type = StatType.fromKey(identifier);

        if (type != null) {
            // AHORA LAS CLAVES ESTÁN EN STATTYPE.JAVA
            return String.valueOf(plugin.getStatsConfig().getInt(type.getKey() + "." + player.getUniqueId().toString(), 0));
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