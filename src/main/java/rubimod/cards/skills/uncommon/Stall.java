package rubimod.cards.skills.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rubimod.cards.BaseCard;
import rubimod.cards.statuses.Stalling;
import rubimod.character.Hegemon;
import rubimod.powers.buff.StallingPower;
import rubimod.util.CardStats;

public class Stall extends BaseCard {
    public static final String ID = ("rubimod:" + Stall.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 4;

    public Stall() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setSelfRetain(false, true);
        setCustomVar("Stall", 2);
        cardsToPreview = new Stalling().setStrengthLoss(magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));
        this.addToBot(new ApplyPowerAction(p, p, new StallingPower(p, customVar("Stall"))));
        this.addToBot(new MakeTempCardInHandAction(new Stalling().setStrengthLoss(this.magicNumber)));
    }

    @Override
    protected void upgradeMagicNumber(int amount) {
        super.upgradeMagicNumber(amount);
        if (cardsToPreview.cardID.equals(Stalling.ID)) {
            ((Stalling) cardsToPreview).setStrengthLoss(magicNumber);
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Stall();
    }
}
