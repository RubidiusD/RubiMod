package HegemonMod.cards.powers.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.cards.GuardiansPrice;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.GuardiansBlessingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GuardiansBlessing extends BaseCard {
    public static final String ID = ("HegemonMod:" + GuardiansBlessing.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;

    public GuardiansBlessing() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setInnate(false, true);
        cardsToPreview = new GuardiansPrice();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GuardiansBlessingPower(p, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new GuardiansBlessing(); }
}