package rubimod.cards.powers.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.buff.GentlePower;
import rubimod.util.CardStats;

public class GentleTouch extends BaseCard {
    public static final String ID = ("rubimod:" + GentleTouch.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public GentleTouch() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new GentlePower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new GentleTouch();
    }
}
