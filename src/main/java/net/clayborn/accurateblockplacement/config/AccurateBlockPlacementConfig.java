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

    public static void save() {
        File configFile = FabricLoader.getInstance().getConfigDir().resolve("accurateblockplacement.properties").toFile();

        try (Writer writer = new FileWriter(configFile)) {
        Properties properties = new Properties();
        properties.setProperty(PLACEMENT_KEY, Boolean.toString(isAccurateBlockPlacementEnabled));
        properties.setProperty(BREAKING_KEY, Boolean.toString(isFastBlockBreakingEnabled));
        properties.setProperty(CONFIRMATION_KEY, String.valueOf(confirmation));
        properties.setProperty(CONFIRMATION_TYPE_KEY, String.valueOf(confirmationType));
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            save();
        }
    }
}
