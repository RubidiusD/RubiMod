//package rubimod.patches;
//
//
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
//import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import rubimod.powers.buff.LearnedPower;
//
//@SpirePatch2(
//        clz= MakeTempCardInHandAction.class,
//        method= SpirePatch.CONSTRUCTOR,
//        paramtypez={
//                AbstractCard.class,
//                int.class
//        }
//)
//public class LearnedHandManyPatch
//{
//    public static void PostFix(AbstractCard ___c)
//    {
//        if (___c.type != AbstractCard.CardType.STATUS && ___c.type != AbstractCard.CardType.CURSE && ___c.cost > 0 && AbstractDungeon.player.hasPower(LearnedPower.POWER_ID))
//        {
//            ___c.costForTurn = ___c.cost -= 1;
//            ___c.isCostModified = true;
//        }
//    }
//}
