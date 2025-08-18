package rubimod.cards.powers.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.cards.skills.Vector;
import rubimod.character.Hegemon;
import rubimod.powers.buff.PlagueBearerPower;
import rubimod.util.CardStats;

public class PlagueBearer extends BaseCard {
    public static final String ID = ("rubimod:" + PlagueBearer.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public PlagueBearer() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
        cardsToPreview = new Vector();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PlagueBearerPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new PlagueBearer();
    }
}
