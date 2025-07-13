package rubimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class CloakofNight extends BaseCard {
    public static final String ID = makeID(CloakofNight.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 8;

    public CloakofNight() {
        super(ID, info); // calls the parent constructor

        setBlock(BLOCK); // self-explanatory
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CloakofNight();
    }
}
