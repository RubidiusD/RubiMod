package HegemonMod.cards.skills.uncommon;

import HegemonMod.actions.ToxicityAction;
import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;

public class Unflex extends BaseCard {
    public static final String ID = ("HegemonMod:" + Unflex.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Unflex() {
        super(ID, info); // calls the parent constructor

        setMagic(2, 2);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ToxicityAction(p, magicNumber));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new Unflex(); }
}