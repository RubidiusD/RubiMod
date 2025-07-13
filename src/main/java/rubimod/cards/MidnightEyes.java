package rubimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class MidnightEyes extends BaseCard {
    public static final String ID = makeID(MidnightEyes.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public MidnightEyes() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryAction(magicNumber));
        if (magicNumber != baseMagicNumber) {
            addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 1)));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MidnightEyes();
    }
}
