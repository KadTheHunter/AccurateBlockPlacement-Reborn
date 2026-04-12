package net.clayborn.accurateblockplacement.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import static net.clayborn.accurateblockplacement.AccurateBlockPlacementMod.*;

import net.fabricmc.loader.api.FabricLoader;

public class AccurateBlockPlacementConfig {

    private static final String PLACEMENT_KEY = "accurateplace-enabled";
    private static final String BREAKING_KEY = "fastbreak-enabled";

    final public static boolean DEFAULT_ACCURATE_PLACEMENT_ENABLED = true;
    final public static boolean DEFAULT_FAST_BREAKING_ENABLED = false;

    private static final String CONFIRMATION_KEY = "confirmation";
    final public static boolean DEFAULT_CONFIRMATION = true;
    public static boolean confirmation;

    private static final String CONFIRMATION_TYPE_KEY = "confirmationType";
    final public static boolean DEFAULT_CONFIRMATION_TYPE = true;
    public static boolean confirmationType;

    private static final String TOOLS_ENABLED_KEY = "tools-enabled";
    final public static boolean DEFAULT_TOOLS_ENABLED = true;
    public static boolean toolsEnabled;

    private static final String BUCKET_ENABLED_KEY = "bucket-enabled";
    final public static boolean DEFAULT_BUCKET_ENABLED = true;
    public static boolean bucketEnabled;

    private static final String ARMOR_STAND_ENABLED_KEY = "armorstand-enabled";
    final public static boolean DEFAULT_ARMOR_STAND_ENABLED = true;
    public static boolean armorStandEnabled;

    private static final String ITEM_FRAME_ENABLED_KEY = "itemframe-enabled";
    final public static boolean DEFAULT_ITEM_FRAME_ENABLED = true;
    public static boolean itemFrameEnabled;

    private static final String SPAWN_EGGS_ENABLED_KEY = "spawneggs-enabled";
    final public static boolean DEFAULT_SPAWN_EGGS_ENABLED = true;
    public static boolean spawnEggsEnabled;

    public static void save() {
        File configFile = FabricLoader.getInstance().getConfigDir().resolve("accurateblockplacement.properties").toFile();

        try (Writer writer = new FileWriter(configFile)) {
        Properties properties = new Properties();
        properties.setProperty(PLACEMENT_KEY, Boolean.toString(isAccurateBlockPlacementEnabled));
        properties.setProperty(BREAKING_KEY, Boolean.toString(isFastBlockBreakingEnabled));
        properties.setProperty(CONFIRMATION_KEY, String.valueOf(confirmation));
        properties.setProperty(CONFIRMATION_TYPE_KEY, String.valueOf(confirmationType));
        properties.setProperty(TOOLS_ENABLED_KEY, String.valueOf(toolsEnabled));
        properties.setProperty(BUCKET_ENABLED_KEY, String.valueOf(bucketEnabled));
        properties.setProperty(ARMOR_STAND_ENABLED_KEY, String.valueOf(armorStandEnabled));
        properties.setProperty(ITEM_FRAME_ENABLED_KEY, String.valueOf(itemFrameEnabled));
        properties.setProperty(SPAWN_EGGS_ENABLED_KEY, String.valueOf(spawnEggsEnabled));
        properties.store(writer, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void load() {
        File configFile = FabricLoader.getInstance().getConfigDir().resolve("accurateblockplacement.properties").toFile();
        if (!configFile.exists()) {
            try (Writer writer = new FileWriter(configFile)) {
                Properties properties = new Properties();
                properties.setProperty(PLACEMENT_KEY, Boolean.toString(DEFAULT_ACCURATE_PLACEMENT_ENABLED));
                properties.setProperty(BREAKING_KEY, Boolean.toString(DEFAULT_FAST_BREAKING_ENABLED));
                properties.setProperty(CONFIRMATION_KEY, String.valueOf(DEFAULT_CONFIRMATION));
                properties.setProperty(CONFIRMATION_TYPE_KEY, String.valueOf(DEFAULT_CONFIRMATION_TYPE));
                properties.setProperty(TOOLS_ENABLED_KEY, String.valueOf(DEFAULT_TOOLS_ENABLED));
                properties.setProperty(BUCKET_ENABLED_KEY, String.valueOf(DEFAULT_BUCKET_ENABLED));
                properties.setProperty(ARMOR_STAND_ENABLED_KEY, String.valueOf(DEFAULT_ARMOR_STAND_ENABLED));
                properties.setProperty(ITEM_FRAME_ENABLED_KEY, String.valueOf(DEFAULT_ITEM_FRAME_ENABLED));
                properties.setProperty(SPAWN_EGGS_ENABLED_KEY, String.valueOf(DEFAULT_SPAWN_EGGS_ENABLED));
                properties.store(writer, null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (Reader reader = new FileReader(configFile)) {
            Properties properties = new Properties();
            properties.load(reader);
            isAccurateBlockPlacementEnabled = Boolean.parseBoolean(properties.getProperty(PLACEMENT_KEY, String.valueOf(DEFAULT_ACCURATE_PLACEMENT_ENABLED)));
            isFastBlockBreakingEnabled = Boolean.parseBoolean(properties.getProperty(BREAKING_KEY, String.valueOf(DEFAULT_FAST_BREAKING_ENABLED)));
            confirmation = Boolean.parseBoolean(properties.getProperty(CONFIRMATION_KEY, String.valueOf(DEFAULT_CONFIRMATION)));
            confirmationType = Boolean.parseBoolean(properties.getProperty(CONFIRMATION_TYPE_KEY, String.valueOf(DEFAULT_CONFIRMATION_TYPE)));
            toolsEnabled = Boolean.parseBoolean(properties.getProperty(TOOLS_ENABLED_KEY, String.valueOf(DEFAULT_TOOLS_ENABLED)));
            bucketEnabled = Boolean.parseBoolean(properties.getProperty(BUCKET_ENABLED_KEY, String.valueOf(DEFAULT_BUCKET_ENABLED)));
            armorStandEnabled = Boolean.parseBoolean(properties.getProperty(ARMOR_STAND_ENABLED_KEY, String.valueOf(DEFAULT_ARMOR_STAND_ENABLED)));
            itemFrameEnabled = Boolean.parseBoolean(properties.getProperty(ITEM_FRAME_ENABLED_KEY, String.valueOf(DEFAULT_ITEM_FRAME_ENABLED)));
            spawnEggsEnabled = Boolean.parseBoolean(properties.getProperty(SPAWN_EGGS_ENABLED_KEY, String.valueOf(DEFAULT_SPAWN_EGGS_ENABLED)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            save();
        }
    }
}
