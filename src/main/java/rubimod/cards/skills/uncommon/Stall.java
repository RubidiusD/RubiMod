package rubimod.cards.skills.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Stalling;
import rubimod.util.CardStats;

public class Stall extends BaseCard {
    public static final String ID = makeID(Stall.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 5;

    public Stall() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setSelfRetain(false, true);
        setCustomVar("Stall", 2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));
        this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber)));
        this.addToBot(new ApplyPowerAction(p, p, new Stalling(p, customVar("Stall"))));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Stall();
    }
}
