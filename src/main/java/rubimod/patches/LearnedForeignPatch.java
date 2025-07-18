package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.watcher.ForeignInfluenceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rubimod.powers.buff.LearnedPower;

@SpirePatch2(
        clz= ForeignInfluenceAction.class,
        method= "update",
        paramtypez={}
)
public class LearnedForeignPatch {
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
