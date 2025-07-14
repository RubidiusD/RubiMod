package rubimod.cards.skills.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class PathofShadows extends BaseCard {
    public static final String ID = makeID(PathofShadows.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 6;
    private static final int MAGIC = 2;

    public PathofShadows() {
        super(ID, info); // calls the parent constructor

        setBlock(BLOCK); // self-explanatory
        setMagic(MAGIC); // self-explanatory
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new GainBlockAction(p, p, block));
        }
        addToBot(new ApplyPowerAction(p, p, new NoBlockPower(p, 1, false)));
        addToBot(new ApplyPowerAction(p, p, new BlurPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PathofShadows();
    }
}
