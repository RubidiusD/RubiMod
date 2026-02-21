package HegemonMod.cards.skills.uncommon;

import HegemonMod.actions.ToxicityAction;
import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SoberUp extends BaseCard {
    public static final String ID = ("HegemonMod:" + SoberUp.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    public SoberUp() {
        super(ID, info); // calls the parent constructor

        setMagic(2, 1);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ToxicityAction(p, -1));
        addToBot(new DrawCardAction(magicNumber));
    }

    @Override public AbstractCard makeCopy() { return new SoberUp(); }
}