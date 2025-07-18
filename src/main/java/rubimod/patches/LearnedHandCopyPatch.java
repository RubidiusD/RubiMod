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
    public static AbstractCard PostFix(AbstractCard card)
    {
        if (card.type != AbstractCard.CardType.STATUS && card.type != AbstractCard.CardType.CURSE && card.cost > 0 && AbstractDungeon.player.hasPower(LearnedPower.POWER_ID))
        {
            card.costForTurn = card.cost -= 1;
            card.isCostModified = true;
        }
        return card;
    }
}
