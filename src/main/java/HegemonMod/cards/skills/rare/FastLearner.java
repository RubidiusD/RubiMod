package HegemonMod.cards.skills.rare;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.LearnedPower;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class FastLearner extends BaseCard {
    public static final String ID = ("HegemonMod:" + FastLearner.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            -2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public FastLearner() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("Retain", magicNumber, -1);
    }

    @Override public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override public void triggerWhenDrawn() {
        setCustomVar("Retain", magicNumber, -1);
        addToTop(new ApplyPowerAction(player, player, new LearnedPower(player)));
        selfRetain = true;
    }

    @Override public void onRetained() {
        upgradeCustomVar("Retain");
        if (customVar("Retain") == 0) {
            selfRetain = false;
        }
    }

    @Override public void onMoveToDiscard() {
        addToBot(new RemoveSpecificPowerAction(player, player, LearnedPower.POWER_ID));
    }

    @Override public AbstractCard makeCopy() { return new FastLearner(); }

    @SpirePatch2(clz= FastLearner.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "BuxomMod")
    public static class BouncyPatch {
        @SpireInstrumentPatch public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("setCustomVar")) {
                        m.replace("$_ = $proceed($$); tags.add(BuxomMod.patches.CustomTags.BOUNCY);");
                    }
                }
            };
        }
    }
}
