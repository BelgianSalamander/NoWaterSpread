package me.salamander.nowaterspread.mixin;

import me.salamander.nowaterspread.NoWaterSpread;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterFluid.class)
public abstract class MixinWaterFluid extends FlowingFluid {
    @Override
    public void tick(Level level, BlockPos pos, FluidState state) {
        if (level.getGameRules().getBoolean(NoWaterSpread.GAME_RULE_WATER_SPREAD)) {
            super.tick(level, pos, state);
        }
    }
}
