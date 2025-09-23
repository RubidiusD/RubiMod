package HegemonMod.cards.skills.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.NoBlockPower;

public class PathOfShadows extends BaseCard {
    public static final String ID = ("HegemonMod:" + PathOfShadows.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = -1;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public PathOfShadows() {
        super(ID, info); // calls the parent constructor

        setBlock(BLOCK, UPG_BLOCK); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i != magicNumber; i++)
            addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new NoBlockPower(p, 1, false)));
        addToBot(new ApplyPowerAction(p, p, new BlurPower(p, 1)));
    }

    @Override
    public void triggerWhenDrawn() {
        this.shuffleBackIntoDrawPile = false;
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PathOfShadows();
    }
}
