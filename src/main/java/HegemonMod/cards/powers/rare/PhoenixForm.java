package HegemonMod.cards.powers.rare;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.PhoenixPower;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PhoenixForm extends BaseCard {
    public static final String ID = ("HegemonMod:" + PhoenixForm.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;

    public PhoenixForm() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setEthereal(true);
        setCostUpgrade(2);

        addTag(BaseModCardTags.FORM);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PhoenixPower(p, magicNumber, 1)));
    }

    @Override public AbstractCard makeCopy() { // Optional
        return new PhoenixForm();
    }
}
