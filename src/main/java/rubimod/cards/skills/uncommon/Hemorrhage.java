package rubimod.cards.skills.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Bleeding;
import rubimod.powers.debuff.LeechToxin;
import rubimod.util.CardStats;

public class Hemorrhage extends BaseCard {
    public static final String ID = makeID(Hemorrhage.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 1;

    public Hemorrhage() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new Bleeding(m, p)));
        addToBot(new DrawCardAction(1));
        if (magicNumber > 0) {
            addToBot(new ApplyPowerAction(m, p, new LeechToxin(m, p, magicNumber)));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Hemorrhage();
    }
}
