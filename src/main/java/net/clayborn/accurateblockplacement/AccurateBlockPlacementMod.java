package net.clayborn.accurateblockplacement;

import net.clayborn.accurateblockplacement.config.AccurateBlockPlacementConfig;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.KeyMapping.Category;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;

public class AccurateBlockPlacementMod implements ClientModInitializer
{
	public static Boolean disableNormalItemUse = false;

	public static boolean isAccurateBlockPlacementEnabled;
	public static boolean isFastBlockBreakingEnabled;

	public static Minecraft MC;
	
	@Override
	public void onInitializeClient()
	{
		AccurateBlockPlacementConfig.load();

		MC = Minecraft.getInstance();

		Category keybindCategory = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("accurateblockplacement", "category"));

		KeyMapping place_keybind = KeyMappingHelper.registerKeyMapping(new KeyMapping("net.clayborn.accurateblockplacement.togglevanillaplacement", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, keybindCategory));

		KeyMapping break_keybind = KeyMappingHelper.registerKeyMapping(new KeyMapping("net.clayborn.accurateblockplacement.togglefastbreaking", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, keybindCategory));
		
		ClientTickEvents.END_CLIENT_TICK.register(e -> {
			while(place_keybind.consumeClick()) {
				isAccurateBlockPlacementEnabled = !isAccurateBlockPlacementEnabled;
				AccurateBlockPlacementConfig.save();
				if (MC.player != null && AccurateBlockPlacementConfig.confirmation) {
					Component message = isAccurateBlockPlacementEnabled
							? Component.translatable("net.clayborn.accurateblockplacement.modplacementmodemessage")
							: Component.translatable("net.clayborn.accurateblockplacement.vanillaplacementmodemessage");

					if (AccurateBlockPlacementConfig.confirmationType) {
						MC.player.sendOverlayMessage(message);
					} else {
						MC.player.sendSystemMessage(message);
					}
				}
			}
			while(break_keybind.consumeClick()) {
				isFastBlockBreakingEnabled = !isFastBlockBreakingEnabled;
				AccurateBlockPlacementConfig.save();
				if (MC.player != null && AccurateBlockPlacementConfig.confirmation) {
					Component message = isFastBlockBreakingEnabled
							? Component.translatable("net.clayborn.accurateblockplacement.fastbreakingenabled")
							: Component.translatable("net.clayborn.accurateblockplacement.fastbreakingdisabled");

					if (AccurateBlockPlacementConfig.confirmationType) {
						MC.player.sendOverlayMessage(message);
					} else {
						MC.player.sendSystemMessage(message);
					}
				}
			}
		});
	}
}