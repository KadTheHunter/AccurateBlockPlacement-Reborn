package net.clayborn.accurateblockplacement.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.util.NullScreenFactory;

import net.fabricmc.loader.api.FabricLoader;

public class ModMenuPlugin implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config")) {
            return AccurateBlockPlacementConfigScreen::createConfigScreen;
        } else {
            return new NullScreenFactory<>();
        }
    }
}
