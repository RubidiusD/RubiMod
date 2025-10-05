package HegemonMod.cards.skills.common;

import HegemonMod.cards.BaseCard;
import HegemonMod.cards.attacks.Punish;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FalseOpening extends BaseCard {
    public static final String ID = ("HegemonMod:" + FalseOpening.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    public FalseOpening() {
        super(ID, info); // calls the parent constructor

        setBlock(6, 3);

        this.cardsToPreview = new Punish();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new MakeTempCardInHandAction(cardsToPreview.makeStatEquivalentCopy()));
    }

    @Override public void upgrade() {
        super.upgrade();
        cardsToPreview.upgrade();
    }

    @Override public AbstractCard makeCopy() { return new FalseOpening(); }
}