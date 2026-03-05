package HegemonMod.cards.skills.common;

import HegemonMod.cards.BaseCard;
import HegemonMod.cards.skills.Vector;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Stanislavsky extends BaseCard {
    public static final String ID = ("HegemonMod:" + Stanislavsky.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Stanislavsky() {
        super(ID, info); // calls the parent constructor

        setBlock(5);
        setMagic(2);
        setCustomVar("Vectors", 2, 3);

        cardsToPreview = new Vector();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        for (int index = 0; index != magicNumber; index ++) {
            addToBot(new GainBlockAction(p, block));
        }
        if (customVar("Vectors") == 2) {
            addToBot(new MakeTempCardInDrawPileAction(cardsToPreview.makeCopy(), 2, true, true));
        }
        else {
            addToBot(new MakeTempCardInDiscardAction(cardsToPreview.makeCopy(), customVar("Vectors")));
        }
    }

    @Override public AbstractCard makeCopy() { return new Stanislavsky(); }
}