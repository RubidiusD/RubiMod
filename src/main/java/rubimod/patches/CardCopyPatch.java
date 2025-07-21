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
    public static AbstractCard Postfix(AbstractCard __result, AbstractCard ___card) {
        __result.exhaust = ___card.exhaust;
        __result.exhaustOnUseOnce = ___card.exhaustOnUseOnce;
        __result.exhaustOnFire = ___card.exhaustOnFire;
        __result.retain = ___card.retain;


        return __result;
    }
}
