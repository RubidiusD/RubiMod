package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatches2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.MakeTempCardAtBottomOfDeckAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.actions.watcher.ForeignInfluenceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rubimod.powers.buff.LearnedPower;

public class LearnedPatches {
    @SpirePatches2({
            @SpirePatch2(clz = MakeTempCardInHandAction.class, method= "makeNewCard", paramtypez={}),
            @SpirePatch2(clz= MakeTempCardInDiscardAction.class, method= "makeNewCard", paramtypez={})
    })
    public static class LearnedHandPatch {
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

    @SpirePatch2(clz= ForeignInfluenceAction.class, method= "update", paramtypez={})
    public static class LearnedForeignPatch {
        @SpireInsertPatch(rloc=9)
        public static void insert(AbstractCard ___disCard)
        {
            if (___disCard.type != AbstractCard.CardType.STATUS && ___disCard.type != AbstractCard.CardType.CURSE && ___disCard.cost > 0 && AbstractDungeon.player.hasPower(LearnedPower.POWER_ID))
            {
                ___disCard.costForTurn = ___disCard.cost -= 1;
                ___disCard.isCostModified = ___disCard.isCostModifiedForTurn = true;
            }
        }
    }

    @SpirePatch2(clz= MakeTempCardInDrawPileAction.class, method="update", paramtypez={})
    public static class LearnedDrawPilePatch {
        @SpireInsertPatch(rloc=12)
        public static void Insert(AbstractCard ___c)
        {
            if (___c.type != AbstractCard.CardType.STATUS && ___c.type != AbstractCard.CardType.CURSE && ___c.cost > 0 && AbstractDungeon.player.hasPower(LearnedPower.POWER_ID))
            {
                ___c.costForTurn = ___c.cost -= 1;
                ___c.isCostModified = true;
            }
        }
    }

    @SpirePatch2(clz= MakeTempCardAtBottomOfDeckAction.class, method="update", paramtypez={})
    public static class LearnedDrawPileBottomPatch {
        @SpireInsertPatch(rloc=9)
        public static void Insert(AbstractCard ___c)
        {
            if (___c.type != AbstractCard.CardType.STATUS && ___c.type != AbstractCard.CardType.CURSE && ___c.cost > 0 && AbstractDungeon.player.hasPower(LearnedPower.POWER_ID))
            {
                ___c.costForTurn = ___c.cost -= 1;
                ___c.isCostModified = true;
            }
        }
    }

    @SpirePatch2(clz= DiscoveryAction.class, method="update", paramtypez={})
    public static class LearnedDiscoveryPatch
    {
        @SpireInsertPatch(rloc=25)
        public static void Insert(AbstractCard ___disCard, AbstractCard ___disCard2)
        {
            if (___disCard.cost > 0 && AbstractDungeon.player.hasPower(LearnedPower.POWER_ID))
            {
                ___disCard.costForTurn = ___disCard.cost -= 1;
                ___disCard2.costForTurn = ___disCard2.cost -= 1;
                ___disCard.isCostModified = ___disCard2.isCostModified = true;
            }
        }
    }
}
