package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch2(
        clz= AbstractCard.class,
        method= "makeStatEquivalentCopy",
        paramtypez={}
)
public class CardCopyPatch {
    @SpirePostfixPatch
    public static AbstractCard Postfix(AbstractCard __result, AbstractCard __card) {
        __result.exhaust = __card.exhaust;
        __result.exhaustOnUseOnce = __card.exhaustOnUseOnce;
        __result.exhaustOnFire = __card.exhaustOnFire;
        __result.retain = __card.retain;


        return __result;
    }
}
