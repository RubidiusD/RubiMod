package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rubimod.powers.buff.LearnedPower;

@SpirePatch2(
        clz = MakeTempCardInHandAction.class,
        method= "makeNewCard",
        paramtypez={}
)
public class LearnedHandCopyPatch {
    @SpirePostfixPatch
    public static AbstractCard PostFix(AbstractCard __result)
    {
        if (__result.type != AbstractCard.CardType.STATUS && __result.type != AbstractCard.CardType.CURSE && __result.cost > 0 && AbstractDungeon.player.hasPower(LearnedPower.POWER_ID))
        {
            __result.costForTurn = __result.cost -= 1;
            __result.isCostModified = true;
        }
        return __result;
    }
}
