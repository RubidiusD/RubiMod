package HegemonMod.cards.skills.rare;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.debuff.Sin;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Hamartia extends BaseCard {
    public static final String ID = ("HegemonMod:" + Hamartia.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 5;

    public Hamartia() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC);
        setExhaust(true, false);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new Sin(m, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { //Optional
        return new Hamartia();
    }
}
