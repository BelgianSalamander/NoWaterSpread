package me.salamander.nowaterspread;

import me.salamander.nowaterspread.mixin.GameRulesAccess;
import me.salamander.nowaterspread.mixin.GameRulesBooleanValueAccess;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.level.GameRules;

public class NoWaterSpread implements ModInitializer {
    public static final GameRules.Key<GameRules.BooleanValue> GAME_RULE_WATER_SPREAD = GameRulesAccess.invokeRegister(
            "doWaterSpread",
            GameRules.Category.UPDATES,
            GameRulesBooleanValueAccess.invokeCreate(true)
    );

    public static final GameRules.Key<GameRules.BooleanValue> GAME_RULE_LAVA_SPREAD = GameRulesAccess.invokeRegister(
            "doLavaSpread",
            GameRules.Category.UPDATES,
            GameRulesBooleanValueAccess.invokeCreate(true)
    );

    @Override
    public void onInitialize() {

    }
}
