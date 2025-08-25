package rubimod.cards.skills.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

import static com.badlogic.gdx.math.MathUtils.random;

public class Anticipation extends BaseCard {
    public static final String ID = ("rubimod:" + Anticipation.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;
    private static final int ENERGY = 2;
    private static final int TAX = 10;
    private static final int REGEN = 4;

    public Anticipation() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
        setCustomVar("Energy", ENERGY);
        setCustomVar("Tax", TAX);
        setCustomVar("Regen", REGEN);
        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, customVar("Energy"))));
        addToBot(new ApplyPowerAction(p, p, new RegenPower(p, customVar("Regen"))));
        addToBot(new LoseHPAction(p, p, customVar("Tax")));
        if (magicNumber == 5 && random.nextBoolean()) {
            this.exhaustOnUseOnce = true;
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Anticipation();
    }
}
