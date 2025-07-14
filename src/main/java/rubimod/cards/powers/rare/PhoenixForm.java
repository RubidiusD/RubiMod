package rubimod.cards.powers.rare;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.buff.PhoenixPower;
import rubimod.util.CardStats;

public class PhoenixForm extends BaseCard {
    public static final String ID = makeID(PhoenixForm.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;

    public PhoenixForm() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setEthereal(true, false);

        tags.add(BaseModCardTags.FORM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PhoenixPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new PhoenixForm();
    }
}
