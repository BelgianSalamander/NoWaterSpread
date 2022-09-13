package me.salamander.nowaterspread.mixin;

import me.salamander.nowaterspread.client.NoWaterSpreadClient;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.ClipContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BucketItem.class)
public class MixinBucketItem {
    @Redirect(
            method = "use",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/level/ClipContext$Fluid;NONE:Lnet/minecraft/world/level/ClipContext$Fluid;"
            )
    )
    private ClipContext.Fluid redirectFluid(){
        return NoWaterSpreadClient.FLUID_BLOCKS ? ClipContext.Fluid.ANY : ClipContext.Fluid.NONE;
    }
}
