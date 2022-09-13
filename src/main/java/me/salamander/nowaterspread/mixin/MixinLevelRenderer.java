package me.salamander.nowaterspread.mixin;

import me.salamander.nowaterspread.client.NoWaterSpreadClient;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelRenderer.class)
public class MixinLevelRenderer {
    @Redirect(
            method = "renderHitOutline",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;getShape(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;"
            )
    )
    private VoxelShape getFluidShape(BlockState instance, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        VoxelShape defaultShape = instance.getShape(blockGetter, blockPos, collisionContext);

        if (defaultShape.isEmpty() && NoWaterSpreadClient.FLUID_BLOCKS) {
            return instance.getFluidState().getShape(blockGetter, blockPos);
        } else {
            return defaultShape;
        }
    }
}
