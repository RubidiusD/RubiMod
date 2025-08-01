package rubimod.cards.skills.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class Endure extends BaseCard {
    public static final String ID = ("rubimod:" + Endure.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 1;
    private static final int MAGIC = 1;

    public Endure() {
        super(ID, info); // calls the parent constructor

        setBlock(BLOCK, UPG_BLOCK); // self-explanatory
        setMagic(MAGIC); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, magicNumber, false)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Endure();
    }
}
