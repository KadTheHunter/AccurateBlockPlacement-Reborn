package net.clayborn.accurateblockplacement;

import net.clayborn.accurateblockplacement.config.AccurateBlockPlacementConfig;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

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

		KeyMapping place_keybind = KeyBindingHelper.registerKeyBinding(new KeyMapping("net.clayborn.accurateblockplacement.togglevanillaplacement", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, keybindCategory));

		KeyMapping break_keybind = KeyBindingHelper.registerKeyBinding(new KeyMapping("net.clayborn.accurateblockplacement.togglefastbreaking", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, keybindCategory));
		
		ClientTickEvents.END_CLIENT_TICK.register(e -> {
			while(place_keybind.consumeClick()) {
				isAccurateBlockPlacementEnabled = !isAccurateBlockPlacementEnabled;
				AccurateBlockPlacementConfig.save();
				if (MC.player != null && AccurateBlockPlacementConfig.confirmation) {
				MC.player.displayClientMessage(isAccurateBlockPlacementEnabled ? Component.translatable("net.clayborn.accurateblockplacement.modplacementmodemessage") : Component.translatable("net.clayborn.accurateblockplacement.vanillaplacementmodemessage"), AccurateBlockPlacementConfig.confirmationType);
				}
			}
			while(break_keybind.consumeClick()) {
				isFastBlockBreakingEnabled = !isFastBlockBreakingEnabled;
				AccurateBlockPlacementConfig.save();
				if (MC.player != null && AccurateBlockPlacementConfig.confirmation) {
					MC.player.displayClientMessage(isFastBlockBreakingEnabled ? Component.translatable("net.clayborn.accurateblockplacement.fastbreakingenabled") : Component.translatable("net.clayborn.accurateblockplacement.fastbreakingdisabled"), AccurateBlockPlacementConfig.confirmationType);
				}
			}
		});
	}
}