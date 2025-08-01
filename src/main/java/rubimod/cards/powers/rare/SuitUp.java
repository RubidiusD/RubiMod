package rubimod.cards.powers.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.buff.ShadowHand;
import rubimod.util.CardStats;

public class SuitUp extends BaseCard {
    public static final String ID = ("rubimod:" + SuitUp.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 2;

    public SuitUp() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ShadowHand(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new SuitUp();
    }
}
