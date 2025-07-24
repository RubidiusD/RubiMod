package rubimod.cards.skills.common;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.cards.skills.Punish;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class FalseOpening extends BaseCard {
    public static final String ID = makeID(FalseOpening.class.getSimpleName()); // makeID adds the mod name
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

    public FalseOpening() {
        super(ID, info); // calls the parent constructor

        setBlock(BLOCK, UPG_BLOCK); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory

        cardsToPreview = new Punish();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new MakeTempCardInHandAction(new Punish()));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FalseOpening();
    }
}
