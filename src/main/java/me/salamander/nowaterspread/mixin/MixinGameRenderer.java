package me.salamander.nowaterspread.mixin;

import me.salamander.nowaterspread.client.NoWaterSpreadClient;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Redirect(
            method = "pick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;pick(DFZ)Lnet/minecraft/world/phys/HitResult;"
            )
    )
    private HitResult redirectPick(Entity instance, double rayTraceDistance, float partialTicks, boolean rayTraceFluids) {
        return instance.pick(
                rayTraceDistance,
                partialTicks,
                rayTraceFluids || NoWaterSpreadClient.FLUID_BLOCKS
        );
    }
}
