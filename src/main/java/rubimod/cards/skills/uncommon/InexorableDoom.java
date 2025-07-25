package rubimod.cards.skills.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.DoomPower;
import rubimod.util.CardStats;

public class InexorableDoom extends BaseCard {
    public static final String ID = makeID(InexorableDoom.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 25;
    private static final int UPG_MAGIC = -15;

    public InexorableDoom() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
        setCustomVar("Doom", 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new DoomPower(m, customVar("Doom"))));
        if (p.drawPile.size() >= magicNumber)
            this.shuffleBackIntoDrawPile = true;
    }

    @Override
    public void triggerWhenDrawn() {
        this.shuffleBackIntoDrawPile = false;
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new InexorableDoom();
    }
}
