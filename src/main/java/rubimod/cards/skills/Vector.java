package rubimod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.powers.debuff.LeechToxin;
import rubimod.util.CardStats;

public class Vector extends BaseCard {
    public static final String ID = ("rubimod:" + Vector.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Vector() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new LeechToxin(m, p, magicNumber)));
        addToBot(new HealAction(m, p, 1));
        addToBot(new DrawCardAction(1));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Vector();
    }
}
