package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rubimod.powers.buff.LearnedPower;

@SpirePatch2(
        clz= DiscoveryAction.class,
        method="update",
        paramtypez={}
)
public class StudiousDiscoveryPatch
{
    @SpireInsertPatch(
            rloc=13
    )
    public static void Insert()
    {
        if (AbstractDungeon.cardRewardScreen.discoveryCard.cost > 0 && AbstractDungeon.player.hasPower(LearnedPower.POWER_ID))
            AbstractDungeon.cardRewardScreen.discoveryCard.modifyCostForCombat(AbstractDungeon.cardRewardScreen.discoveryCard.cost-1);
    }
}
