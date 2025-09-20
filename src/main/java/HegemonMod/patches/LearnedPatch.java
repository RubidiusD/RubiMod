package HegemonMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatches2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import HegemonMod.powers.buff.LearnedPower;

@SpirePatches2({
        @SpirePatch2(clz= ShowCardAndAddToHandEffect.class, method= SpirePatch.CONSTRUCTOR, paramtypez= {AbstractCard.class, float.class, float.class}),
        @SpirePatch2(clz= ShowCardAndAddToHandEffect.class, method= SpirePatch.CONSTRUCTOR, paramtypez= {AbstractCard.class}),
        @SpirePatch2(clz= ShowCardAndAddToDiscardEffect.class, method= SpirePatch.CONSTRUCTOR, paramtypez= {AbstractCard.class, float.class, float.class}),
        @SpirePatch2(clz= ShowCardAndAddToDiscardEffect.class, method= SpirePatch.CONSTRUCTOR, paramtypez= {AbstractCard.class}),
        @SpirePatch2(clz= ShowCardAndAddToDrawPileEffect.class, method= SpirePatch.CONSTRUCTOR, paramtypez= {AbstractCard.class, boolean.class, boolean.class}),
        @SpirePatch2(clz= ShowCardAndAddToDrawPileEffect.class, method= SpirePatch.CONSTRUCTOR, paramtypez= {AbstractCard.class, float.class, float.class, boolean.class, boolean.class, boolean.class})
})
public class LearnedPatch {
    public static void ApplyLearned(AbstractCard c) {
        if (c.type != AbstractCard.CardType.STATUS && c.type != AbstractCard.CardType.CURSE && AbstractDungeon.player.hasPower(LearnedPower.POWER_ID)) {
            if (c.costForTurn > 0)
                c.costForTurn = c.cost -= 1;
            c.isCostModified = true;
        }
    }

    private static final String[] methods = new String[]{"addToHand", "addToTop", "addToBottom", "addToRandomSpot"};

    private static boolean contains(String methodName) {
        for (String s : methods) {
            if (s.equals(methodName)) {
                return true;
            }
        }
        return false;
    }

    @SpireInstrumentPatch
    public static ExprEditor Instrument() {
        return new ExprEditor() {
            @Override public void edit(MethodCall m) throws CannotCompileException {
                if (contains(m.getMethodName())) {
                    m.replace("HegemonMod.patches.LearnedPatch.ApplyLearned(card); $_ = $proceed($$);");
                }
            }
        };
    }
}
