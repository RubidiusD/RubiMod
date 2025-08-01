package rubimod.cards.powers.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.buff.TacticalMapPower;
import rubimod.util.CardStats;

public class TacticalMap extends BaseCard {
    public static final String ID = ("rubimod:" + TacticalMap.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;

    public TacticalMap() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TacticalMapPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new TacticalMap();
    }
}
