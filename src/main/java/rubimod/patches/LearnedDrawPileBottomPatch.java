package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.common.MakeTempCardAtBottomOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rubimod.powers.buff.LearnedPower;

@SpirePatch2(
        clz= MakeTempCardAtBottomOfDeckAction.class,
        method="update",
        paramtypez={}
)
public class LearnedDrawPileBottomPatch {
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
