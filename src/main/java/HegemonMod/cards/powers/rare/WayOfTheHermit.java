package HegemonMod.cards.powers.rare;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.HermitPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WayOfTheHermit extends BaseCard {
    public static final String ID = ("HegemonMod:" + WayOfTheHermit.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    public WayOfTheHermit() {
        super(ID, info); // calls the parent constructor

        setMagic(6, 2);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HermitPower(p, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new WayOfTheHermit(); }
}