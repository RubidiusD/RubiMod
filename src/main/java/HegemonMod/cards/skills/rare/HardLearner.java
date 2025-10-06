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
import com.megacrit.cardcrawl.powers.watcher.MasterRealityPower;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class HardLearner extends BaseCard {
    public static final String ID = ("HegemonMod:" + HardLearner.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            -2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public HardLearner() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("Retain", MAGIC, UPG_MAGIC);
    }

    @Override public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }

    @Override public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) { }

    @Override public void triggerWhenDrawn() {
        magicNumber = customVar("Retain");
        addToTop(new ApplyPowerAction(player, player, new MasterRealityPower(player)));
        selfRetain = true;
    }

    @Override public void onRetained() {
        magicNumber --;
        if (magicNumber == 0) {
            selfRetain = false;
        }
    }

    @Override public void onMoveToDiscard() {
        addToBot(new RemoveSpecificPowerAction(player, player, MasterRealityPower.POWER_ID));
    }

    @Override public AbstractCard makeCopy() { return new HardLearner(); }

    @SpirePatch2(clz= HardLearner.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "BuxomMod")
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
