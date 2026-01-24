package net.clayborn.accurateblockplacement.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.clayborn.accurateblockplacement.AccurateBlockPlacementMod;
import net.clayborn.accurateblockplacement.IMinecraftClientAccessor;
import net.minecraft.client.Minecraft;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements IMinecraftClientAccessor
{
	@Shadow
	protected abstract void startUseItem();

	@Shadow
	private int rightClickDelay;
	
	@Override
	public void accurateblockplacement_DoItemUseBypassDisable()
	{
		Boolean oldValue = AccurateBlockPlacementMod.disableNormalItemUse;
		AccurateBlockPlacementMod.disableNormalItemUse = false;
		startUseItem();
		AccurateBlockPlacementMod.disableNormalItemUse = oldValue;
	}
	
	@Inject(method = "startUseItem", at = @At("HEAD"), cancellable = true)
	void OnDoItemUse(CallbackInfo info)
	{
		if(AccurateBlockPlacementMod.disableNormalItemUse) {
			info.cancel();
		}
	}
	
	@Override
	public void accurateblockplacement_SetItemUseCooldown(int cooldown)
	{
		rightClickDelay = cooldown;
	}
	
	@Override
	public int accurateblockplacement_GetItemUseCooldown()
	{
		return rightClickDelay;
	}
}
