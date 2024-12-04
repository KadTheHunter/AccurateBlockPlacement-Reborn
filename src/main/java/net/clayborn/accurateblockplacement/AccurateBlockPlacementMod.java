package net.clayborn.accurateblockplacement;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class AccurateBlockPlacementMod implements ClientModInitializer
{
	public static Boolean disableNormalItemUse = false;

	public static boolean isAccurateBlockPlacementEnabled;
	public static boolean isFastBlockBreakingEnabled;

	public static MinecraftClient MC;
	
	@Override
	public void onInitializeClient()
	{
		MC = MinecraftClient.getInstance();

		KeyBinding place_keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding("net.clayborn.accurateblockplacement.togglevanillaplacement", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Accurate Block Placement"));

		KeyBinding break_keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding("net.clayborn.accurateblockplacement.togglefastbreaking", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Fast Block Breaking"));
		
		ClientTickEvents.END_CLIENT_TICK.register(e -> {
			while(place_keybind.wasPressed()) {
				isAccurateBlockPlacementEnabled = !isAccurateBlockPlacementEnabled;
				
				MC.inGameHud.getChatHud().addMessage(isAccurateBlockPlacementEnabled ? Text.translatable("net.clayborn.accurateblockplacement.modplacementmodemessage") : Text.translatable("net.clayborn.accurateblockplacement.vanillaplacementmodemessage"));
			}
			while(break_keybind.wasPressed()) {
				isFastBlockBreakingEnabled = !isFastBlockBreakingEnabled;
				MC.inGameHud.getChatHud().addMessage(isFastBlockBreakingEnabled ? Text.translatable("net.clayborn.accurateblockplacement.fastbreakingenabled") : Text.translatable("net.clayborn.accurateblockplacement.fastbreakingdisabled"));
			}
		});
	}
}