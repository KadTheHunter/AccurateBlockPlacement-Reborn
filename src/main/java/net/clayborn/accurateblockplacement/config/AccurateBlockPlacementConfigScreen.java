package net.clayborn.accurateblockplacement.config;

import static net.clayborn.accurateblockplacement.AccurateBlockPlacementMod.*;

import net.clayborn.accurateblockplacement.config.AccurateBlockPlacementConfig;
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
                .setDefaultValue(ConfirmTypeLabel.HUD)
                .setSaveConsumer((replace) -> AccurateBlockPlacementConfig.confirmationType = replace == ConfirmTypeLabel.HUD)
                .build());
        return builder.build();
    }
}
