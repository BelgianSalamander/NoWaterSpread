package me.salamander.nowaterspread.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MultiPlayerGameMode.class)
public class MixinMultiPlayerGameMode {
    @Shadow @Final private Minecraft minecraft;

    @Redirect(
            method = "destroyBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/material/FluidState;createLegacyBlock()Lnet/minecraft/world/level/block/state/BlockState;"
            )
    )
    private BlockState allowBreakingWater(FluidState instance, BlockPos pos){
        BlockState blockState = this.minecraft.level.getBlockState(pos);

        if (blockState.getBlock() instanceof LiquidBlock) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return instance.createLegacyBlock();
        }
    }
}
