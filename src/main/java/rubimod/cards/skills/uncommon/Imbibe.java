package rubimod.cards.skills.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class Imbibe extends BaseCard {
    public static final String ID = ("rubimod:" + Imbibe.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Imbibe() {
        super(ID, info); // calls the parent constructor

        setBlock(BLOCK, UPG_BLOCK); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Imbibe();
    }
}
