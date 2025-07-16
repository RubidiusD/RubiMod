package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rubimod.powers.buff.LearnedPower;

@SpirePatch2(
        clz= MakeTempCardInDrawPileAction.class,
        method="update",
        paramtypez={}
)
public class StudiousDrawPilePatch {
        @SpireInsertPatch(
                rloc=1
        )
        public static void Insert(AbstractCard ___cardToMake)
        {
            if (___cardToMake.cost > 0 && AbstractDungeon.player.hasPower(LearnedPower.POWER_ID))
                ___cardToMake.modifyCostForCombat(___cardToMake.cost-1);
        }
}
