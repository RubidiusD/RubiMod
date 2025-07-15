package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rubimod.powers.buff.StudiousPower;

@SpirePatch2(
        clz= MakeTempCardInHandAction.class,
        method= SpirePatch.CONSTRUCTOR,
        paramtypez={
                AbstractCard.class,
                boolean.class
        }
)
public class StudiousHandPatch
{
    public static void PostFix(AbstractCard ___c)
    {
        if (___c.cost > 0 && AbstractDungeon.player.hasPower(StudiousPower.POWER_ID))
            ___c.modifyCostForCombat(___c.cost-1);
    }
}
