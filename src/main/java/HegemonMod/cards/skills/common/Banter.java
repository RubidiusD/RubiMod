package HegemonMod.cards.skills.common;

import HegemonMod.actions.XEnergyAction;
import HegemonMod.cards.BaseCard;
import HegemonMod.cards.skills.Vector;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static com.megacrit.cardcrawl.powers.AbstractPower.PowerType.BUFF;

public class Banter extends BaseCard {
    public static final String ID = ("HegemonMod:" + Banter.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF_AND_ENEMY,
            -1 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Banter() {
        super(ID, info); // calls the parent constructor

        setBlock(4, 2);
        setExhaust(true);

        cardsToPreview = new Vector();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new XEnergyAction(energyOnUse, freeToPlayOnce, ()-> {
            addToTop(new GainBlockAction(p, block));
        }, (int max) -> {
            if (max == 0) {
                return;
            }
            int amount = 0;
            for (AbstractPower po : m.powers) {
                if (po.type.equals(BUFF)) {
                    if (!po.canGoNegative && po.amount == -1) {
                        amount ++;
                    }
                    else if (!po.canGoNegative || po.amount > 0) {
                        amount += po.amount;
                    }
                }
            }
            if (amount != 0) {
                addToBot(new MakeTempCardInHandAction(cardsToPreview.makeCopy(), Math.min(amount, max)));
            }
        }));
    }

    @Override public AbstractCard makeCopy() { return new Banter(); }
}