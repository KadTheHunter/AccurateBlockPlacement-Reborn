package net.clayborn.accurateblockplacement.config;

import static net.clayborn.accurateblockplacement.AccurateBlockPlacementMod.*;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class AccurateBlockPlacementConfigScreen {
    public static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("text.autoconfig.accurateblockplacement.title"));
        builder.setSavingRunnable(AccurateBlockPlacementConfig::save);
        ConfigCategory general = builder
                .getOrCreateCategory(Component.translatable("text.autoconfig.accurateblockplacement.title"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        // Accurate placement
        general.addEntry(entryBuilder
                .startBooleanToggle(
                        Component.translatable("text.autoconfig.accurateblockplacement.option.accuratePlacementEnabled"),
                        isAccurateBlockPlacementEnabled)
                .setDefaultValue(AccurateBlockPlacementConfig.DEFAULT_ACCURATE_PLACEMENT_ENABLED)
                .setSaveConsumer((replace) -> isAccurateBlockPlacementEnabled = replace).build());
        // Fast breaking
        general.addEntry(entryBuilder
                .startBooleanToggle(
                        Component.translatable("text.autoconfig.accurateblockplacement.option.fastBreakingEnabled"),
                        isFastBlockBreakingEnabled)
                .setDefaultValue(AccurateBlockPlacementConfig.DEFAULT_FAST_BREAKING_ENABLED)
                .setSaveConsumer((replace) -> isFastBlockBreakingEnabled = replace)
                .build());
        // Confirmation
        general.addEntry(entryBuilder
                .startBooleanToggle(
                        Component.translatable("text.autoconfig.accurateblockplacement.option.confirmation"),
                        AccurateBlockPlacementConfig.confirmation)
                .setDefaultValue(AccurateBlockPlacementConfig.DEFAULT_CONFIRMATION)
                .setSaveConsumer((replace) -> AccurateBlockPlacementConfig.confirmation = replace)
                .build());
        // Confirmation Type
        enum ConfirmTypeLabel {
            CHAT,
            HUD
        }
        general.addEntry(entryBuilder
                .startEnumSelector(Component.translatable("text.autoconfig.accurateblockplacement.option.confirmationType"),
                        ConfirmTypeLabel.class,
                        !AccurateBlockPlacementConfig.confirmationType ? ConfirmTypeLabel.CHAT : ConfirmTypeLabel.HUD)
                .setEnumNameProvider(enumValue -> Component.translatable("text.autoconfig.accurateblockplacement.option.confirmationType." + enumValue.name()))
                .setDefaultValue(ConfirmTypeLabel.HUD)
                .setSaveConsumer((replace) -> AccurateBlockPlacementConfig.confirmationType = replace == ConfirmTypeLabel.HUD)
                .build());
        
        // Item Groups Section
        general.addEntry(entryBuilder
                .startTextDescription(Component.translatable("text.autoconfig.accurateblockplacement.section.itemGroups"))
                .build());
        // Accurate tools
        general.addEntry(entryBuilder
                .startBooleanToggle(
                        Component.translatable("text.autoconfig.accurateblockplacement.option.toolsEnabled"),
                        AccurateBlockPlacementConfig.toolsEnabled)
                .setDefaultValue(AccurateBlockPlacementConfig.DEFAULT_TOOLS_ENABLED)
                .setSaveConsumer((replace) -> AccurateBlockPlacementConfig.toolsEnabled = replace)
                .setTooltip(Component.translatable("text.autoconfig.accurateblockplacement.option.toolsEnabled.tooltip"))
                .build());
        // Accurate buckets
        general.addEntry(entryBuilder
                .startBooleanToggle(
                        Component.translatable("text.autoconfig.accurateblockplacement.option.bucketEnabled"),
                        AccurateBlockPlacementConfig.bucketEnabled)
                .setDefaultValue(AccurateBlockPlacementConfig.DEFAULT_BUCKET_ENABLED)
                .setSaveConsumer((replace) -> AccurateBlockPlacementConfig.bucketEnabled = replace)
                .setTooltip(Component.translatable("text.autoconfig.accurateblockplacement.option.bucketEnabled.tooltip"))
                .build());
        // Accurate armor stands
        general.addEntry(entryBuilder
                .startBooleanToggle(
                        Component.translatable("text.autoconfig.accurateblockplacement.option.armorStandEnabled"),
                        AccurateBlockPlacementConfig.armorStandEnabled)
                .setDefaultValue(AccurateBlockPlacementConfig.DEFAULT_ARMOR_STAND_ENABLED)
                .setSaveConsumer((replace) -> AccurateBlockPlacementConfig.armorStandEnabled = replace)
                .setTooltip(Component.translatable("text.autoconfig.accurateblockplacement.option.armorStandEnabled.tooltip"))
                .build());
        // Accurate item frames
        general.addEntry(entryBuilder
                .startBooleanToggle(
                        Component.translatable("text.autoconfig.accurateblockplacement.option.itemFrameEnabled"),
                        AccurateBlockPlacementConfig.itemFrameEnabled)
                .setDefaultValue(AccurateBlockPlacementConfig.DEFAULT_ITEM_FRAME_ENABLED)
                .setSaveConsumer((replace) -> AccurateBlockPlacementConfig.itemFrameEnabled = replace)
                .setTooltip(Component.translatable("text.autoconfig.accurateblockplacement.option.itemFrameEnabled.tooltip"))
                .build());
        general.addEntry(entryBuilder
                .startBooleanToggle(
                        Component.translatable("text.autoconfig.accurateblockplacement.option.spawnEggsEnabled"),
                        AccurateBlockPlacementConfig.spawnEggsEnabled)
                .setDefaultValue(AccurateBlockPlacementConfig.DEFAULT_SPAWN_EGGS_ENABLED)
                .setSaveConsumer((replace) -> AccurateBlockPlacementConfig.spawnEggsEnabled = replace)
                .setTooltip(Component.translatable("text.autoconfig.accurateblockplacement.option.spawnEggsEnabled.tooltip"))
                .build());
        return builder.build();
    }
}
