package com.xlilith.simplestats;

import java.util.Map;
import java.util.HashMap;

public enum StatType {
    PLAYER_KILLS("player_kills"),
    DEATHS("deaths"),
    MOB_KILLS("mob_kills"),
    ANIMALS_BRED("animals_bred"),
    SWORDS_USED("swords_used"),
    PICKAXES_USED("pickaxes_used"),
    AXES_USED("axes_used"),
    SHOVELS_USED("shovels_used"),
    APPLES_EATEN("apples_eaten"),
    BAKED_POTATOES_EATEN("baked_potatoes_eaten"),
    BEETROOTS_EATEN("beetroots_eaten"),
    BEETROOT_SOUPS_EATEN("beetroot_soups_eaten"),
    BREAD_EATEN("bread_eaten"),
    CARROTS_EATEN("carrots_eaten"),
    CHICKENS_EATEN("chickens_eaten"),
    COOKED_CHICKENS_EATEN("cooked_chickens_eaten"),
    COOKED_CODS_EATEN("cooked_cods_eaten"),
    COOKED_MUTTON_EATEN("cooked_mutton_eaten"),
    COOKED_PORKCHOPS_EATEN("cooked_porkchops_eaten"),
    COOKED_RABBITS_EATEN("cooked_rabbits_eaten"),
    COOKED_SALMON_EATEN("cooked_salmon_eaten"),
    GOLDEN_APPLES_EATEN("golden_apples_eaten"),
    GOLDEN_CARROTS_EATEN("golden_carrots_eaten"),
    MUSHROOM_STEW_EATEN("mushroom_stew_eaten"),
    POISONOUS_POTATOES_EATEN("poisonous_potatoes_eaten"),
    POTATOES_EATEN("potatoes_eaten"),
    PUFFERFISH_EATEN("pufferfish_eaten"),
    RABBIT_STEW_EATEN("rabbit_stew_eaten"),
    ROTTEN_FLESH_EATEN("rotten_flesh_eaten"),
    SPIDER_EYES_EATEN("spider_eyes_eaten"),
    SUSPICIOUS_STEW_EATEN("suspicious_stew_eaten"),
    MELON_SLICES_EATEN("melon_slices_eaten"),
    SWEET_BERRIES_EATEN("sweet_berries_eaten"),
    GLOW_BERRIES_EATEN("glow_berries_eaten"),
    CHORUS_FRUIT_EATEN("chorus_fruit_eaten"),
    DRIED_KELPS_EATEN("dried_kelps_eaten"),
    BEEF_EATEN("beef_eaten"),
    COOKED_BEEF_EATEN("cooked_beef_eaten"),
    PORKCHOP_EATEN("porkchop_eaten"),
    MUTTON_EATEN("mutton_eaten"),
    ENCHANTED_GOLDEN_APPLES_EATEN("enchanted_golden_apples_eaten"),
    PUMPKIN_PIES_EATEN("pumpkin_pies_eaten"),
    CAKES_EATEN("cakes_eaten"),
    COOKIES_EATEN("cookies_eaten"),
    TROPICAL_FISH_EATEN("tropical_fish_eaten"),
    CODS_EATEN("cods_eaten"),
    RABBITS_EATEN("rabbits_eaten"),
    MILK_BUCKETS_CONSUMED("milk_buckets_consumed"),
    HONEY_BOTTLES_CONSUMED("honey_bottles_consumed"),
    POTIONS_CONSUMED("potions_consumed"),
    ACACIA_SAPLING_PLANTED("acacia_sapling_planted"),
    BIRCH_SAPLING_PLANTED("birch_sapling_planted"),
    OAK_SAPLING_PLANTED("oak_sapling_planted"),
    DARK_OAK_SAPLING_PLANTED("dark_oak_sapling_planted"),
    SPRUCE_SAPLING_PLANTED("spruce_sapling_planted"),
    JUNGLE_SAPLING_PLANTED("jungle_sapling_planted"),
    CHERRY_SAPLING_PLANTED("cherry_sapling_planted"),
    WHEAT_PLANTED("wheat_planted"),
    WHEAT_HARVESTED("wheat_harvested"),
    BAMBOO_PLANTED("bamboo_planted"),
    BAMBOO_HARVESTED("bamboo_harvested"),
    BEETROOT_PLANTED("beetroot_planted"),
    BEETROOT_HARVESTED("beetroot_harvested"),
    CARROT_PLANTED("carrot_planted"),
    CARROT_HARVESTED("carrot_harvested"),
    COCOA_BEANS_PLANTED("cocoa_beans_planted"),
    COCOA_BEANS_HARVESTED("cocoa_beans_harvested"),
    MELON_PLANTED("melon_planted"),
    MELON_HARVESTED("melon_harvested"),
    NETHER_WART_PLANTED("nether_wart_planted"),
    NETHER_WART_HARVESTED("nether_wart_harvested"),
    POTATO_PLANTED("potato_planted"),
    POTATO_HARVESTED("potato_harvested"),
    PUMPKIN_PLANTED("pumpkin_planted"),
    PUMPKIN_HARVESTED("pumpkin_harvested"),
    SUGAR_CANE_PLANTED("sugar_cane_planted"),
    SUGAR_CANE_HARVESTED("sugar_cane_harvested"),
    SWEET_BERRIES_PLANTED("sweet_berries_planted"),
    SWEET_BERRIES_HARVESTED("sweet_berries_harvested"),
    TORCHFLOWER_PLANTED("torchflower_planted"),
    TORCHFLOWER_HARVESTED("torchflower_harvested"),

    // Asesino
    SWORD_KILLS("sword_kills"),
    AXE_KILLS("axe_kills"),
    EXPLOSIVE_POTIONS_THROWN("explosive_potions_thrown"),
    PROJECTILE_KILLS("projectile_kills"),
    MULTI_KILLS("multi_kills"),
    CRITICAL_HITS("critical_hits"),
    INVISIBILITY_DAMAGE("invisibility_damage"),
    POISON_DAMAGE("poison_damage"),
    UNARMED_KILLS("unarmed_kills"),

    // Explorador
    COMPASS_TIME_SECONDS("compass_time_seconds"),
    FOOD_CONSUMED("food_consumed"),
    MAPS_DRAWN("maps_drawn"),
    BANNER_MAP_MARKS("banner_map_marks"),
    TREASURE_CHESTS_OPENED("treasure_chests_opened"),
    INSPECTIONS_SUSPICIOUS("inspections_suspicious"),
    STRUCTURE_CHESTS_OPENED("structure_chests_opened"),
    BIOME_ENTRIES("biome_entries"),

    // Caballero
    UNDEAD_KILLS("undead_kills"),
    ARTHROPOD_KILLS("arthropod_kills"),
    GHAST_KILLS("ghast_kills"),
    ENDERMAN_KILLS("enderman_kills"),
    HOGLIN_KILLS("hoglin_kills"),
    SHIELD_BLOCKS("shield_blocks"),
    SWEEPING_ATTACKS("sweeping_attacks"),
    MOB_CRITICAL_HITS("mob_critical_hits"),
    
    // Cocinero
    FOOD_IN_BARREL("food_in_barrel"),
    FISH_COOKED("fish_cooked"),
    MEAT_COOKED("meat_cooked"),
    STEWS_CRAFTED("stews_crafted"),
    VEGGIES_COOKED("veggies_cooked"),

    // Ingeniero
    PLANKS_CRAFTED("planks_crafted"),
    STONECUTTER_USED("stonecutter_used"),
    REDSTONE_COMPONENTS_CRAFTER("redstone_components_crafted"),
    MACHINES_CRAFTER("machines_crafted"),
    CONSTRUCTOR_BLOCKS_PLACED("constructor_blocks_placed"),
    REDSTONE_SOLID_BLOCKS_PLACED("redstone_solid_blocks_placed"),
    REDSTONE_TRANSPARENT_BLOCKS_PLACED("redstone_transparent_blocks_placed"),

    // Mago
    POTIONS_BREWED("potions_brewed"),
    POTIONS_DRANK("potions_drank"),
    BOOKSHELF_SLOTS_FILLED("bookshelf_slots_filled"),
    XP_USED_ENCHANT("xp_used_enchant"),
    ENCHANTS_BOOK("enchants_book"),
    ENCHANTS_ARMOR("enchants_armor"),
    ENCHANTS_WEAPON("enchants_weapon"),
    ENCHANTS_TOOL("enchants_tool"),

    // Pescador
    FISH_CAUGHT("fish_caught"),
    TREASURE_CAUGHT("treasure_caught"),
    JUNK_CAUGHT("junk_caught"),
    FISH_FOR_EMERALD_TRADES("fish_for_emerald_trades"),

    // Granjero
    BONE_MEAL_USED("bone_meal_used"),
    HOES_USED("hoes_used"),
    COMPOSTER_USED("composter_used"),
    CROPS_HARVESTED("crops_harvested"),
    COWS_MILKED("cows_milked"),
    SHEEP_SHEARED("sheep_sheared"),
    FARM_ANIMALS_KILLED("farm_animals_killed"),

    // Minero
    STONE_MINED("stone_mined"),
    ORE_MINED("ore_mined"),
    ORE_SMELTED("ore_smelted"),
    RAILS_PLACED("rails_placed"),

    ;

    // más

    private final String key;
    private static final Map<String, StatType> BY_KEY = new HashMap<>();

    static {
        for (StatType st : values()) {
            BY_KEY.put(st.key, st);
        }
    }

    StatType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static StatType fromKey(String key) {
        return BY_KEY.get(key);
    }
}