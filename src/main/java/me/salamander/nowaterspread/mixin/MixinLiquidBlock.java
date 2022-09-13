package me.salamander.nowaterspread.mixin;

import me.salamander.nowaterspread.client.NoWaterSpreadClient;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LiquidBlock.class)
public abstract class MixinLiquidBlock extends Block {
    public MixinLiquidBlock(Properties properties) {
        super(properties);
        throw new RuntimeException("Mixin failed to apply!");
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        if (NoWaterSpreadClient.FLUID_BLOCKS) {
            return false;
        } else {
            return super.canBeReplaced(state, useContext);
        }
    }

    @Override
    public boolean canBeReplaced(BlockState state, Fluid fluid) {
        if (NoWaterSpreadClient.FLUID_BLOCKS) {
            return false;
        } else {
            return super.canBeReplaced(state, fluid);
        }
    }
}
