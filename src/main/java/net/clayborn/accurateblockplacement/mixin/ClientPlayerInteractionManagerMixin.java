package net.clayborn.accurateblockplacement.mixin;

import net.clayborn.accurateblockplacement.AccurateBlockPlacementMod;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @ModifyConstant(method = "updateBlockBreakingProgress", constant = @Constant(intValue = 5))
    private int FastBlockBreaking(int value) {
        return AccurateBlockPlacementMod.isFastBlockBreakingEnabled ? 0 : value;
    }
}