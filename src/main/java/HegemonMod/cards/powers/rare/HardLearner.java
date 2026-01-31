package HegemonMod.cards.powers.rare;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.EnrichedPower;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatches2;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class HardLearner extends BaseCard {
    public static final String ID = ("HegemonMod:" + HardLearner.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.NONE,
            -2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public HardLearner() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }

    @Override public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) { }

    @Override public void triggerWhenDrawn() {
        addToTop(new ApplyPowerAction(player, player, new EnrichedPower(player, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new HardLearner(); }

    @SpirePatches2({
            @SpirePatch2(clz= HardLearner.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "BuxomMod"),
            @SpirePatch2(clz= FastLearner.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "BuxomMod")
    })
    public static class BouncyPatch {
        @SpireInstrumentPatch public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("setMagic")) {
                        m.replace("$_ = $proceed($$); tags.add(BuxomMod.patches.CustomTags.BOUNCY);");
                    }
                }
            };
        }
    }
}
