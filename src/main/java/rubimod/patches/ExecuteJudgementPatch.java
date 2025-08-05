package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Judgement;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.powers.debuff.LegacyofSin;

//@SpirePatch2(
//        clz= JudgementAction.class,
//        method= SpirePatch.CONSTRUCTOR,
//        paramtypez = {AbstractCreature.class, int.class}
//)
//public class ExecuteJudgementPatch {
//    @SpirePostfixPatch
//    public static void Postfix(int __cutoff, int cutoff, AbstractCreature __target) {
//        if (__target.hasPower(LegacyofSin.POWER_ID)) {
//            __cutoff = (int) (((LegacyofSin) __target.getPower(LegacyofSin.POWER_ID)).calculate((float) cutoff));
//        };
//    }
//}

@SpirePatch2(
        clz= Judgement.class,
        method= "use",
        paramtypez = {AbstractPlayer.class, AbstractMonster.class}
)
public class ExecuteJudgementPatch {
    @SpireInsertPatch(rloc= 5)
    public static void insert(AbstractCard __instance, AbstractMonster m) {
        if (m.hasPower(LegacyofSin.POWER_ID))
            __instance.magicNumber = (int) (((LegacyofSin) m.getPower(LegacyofSin.POWER_ID)).calculate((float) __instance.magicNumber));
    }
}