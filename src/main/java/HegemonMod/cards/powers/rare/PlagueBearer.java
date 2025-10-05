package HegemonMod.cards.powers.rare;

import HegemonMod.cards.BaseCard;
import HegemonMod.cards.skills.Vector;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.PlagueBearerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PlagueBearer extends BaseCard {
    public static final String ID = ("HegemonMod:" + PlagueBearer.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    public PlagueBearer() {
        super(ID, info); // calls the parent constructor

        setMagic(1, 1);

        cardsToPreview = new Vector();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PlagueBearerPower(p, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new PlagueBearer(); }
}