package HegemonMod.cards.powers.rare;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.LearnedPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.MasterReality;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FastLearner extends BaseCard {
    public static final String ID = ("HegemonMod:" + FastLearner.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    public FastLearner() {
        super(ID, info); // calls the parent constructor
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LearnedPower(p)));

        if (timesUpgraded != 0) {
            addToBot(new EmptyDeckShuffleAction());
            addToBot(new MakeTempCardInDiscardAction(cardsToPreview.makeCopy(), 1));
        }
    }

    @Override
    public void upgrade() {
        super.upgrade();
        this.cardsToPreview = new MasterReality();
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new FastLearner();
    }
}
