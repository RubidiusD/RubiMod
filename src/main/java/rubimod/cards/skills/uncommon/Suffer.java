package rubimod.cards.skills.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.ApplyNecroToxinAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Sin;
import rubimod.util.CardStats;

public class Suffer extends BaseCard {
    public static final String ID = makeID(Suffer.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 5;

    public Suffer() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setCustomVar("Sin", 0, 2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyNecroToxinAction(m, p, magicNumber));
        if (customVar("Sin") != 0)
            addToBot(new ApplyPowerAction(p, p, new Sin(p, customVar("Sin"))));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Suffer();
    }
}
