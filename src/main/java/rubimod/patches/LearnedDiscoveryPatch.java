package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rubimod.powers.buff.LearnedPower;

@SpirePatch2(
        clz= DiscoveryAction.class,
        method="update",
        paramtypez={}
)
public class LearnedDiscoveryPatch
{
    @SpireInsertPatch(
            loc=67
    )
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
