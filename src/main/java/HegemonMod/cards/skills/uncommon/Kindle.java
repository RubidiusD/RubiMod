package HegemonMod.cards.skills.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.cards.attacks.Blaze;
import HegemonMod.character.Hegemon;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Kindle extends BaseCard {
    public static final String ID = ("HegemonMod:" + Kindle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Kindle() {
        super(ID, info); // calls the parent constructor

        setMagic(1, 1);
        cardsToPreview = new Blaze();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddTemporaryHPAction(p, p, 12));
        addToBot(new MakeTempCardInDiscardAction(cardsToPreview.makeCopy(), magicNumber));
    }

    @Override public AbstractCard makeCopy() { return new Kindle(); }
}