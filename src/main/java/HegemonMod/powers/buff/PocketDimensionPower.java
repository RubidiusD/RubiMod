package HegemonMod.powers.buff;

import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class PocketDimensionPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + PocketDimensionPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public PocketDimensionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        amount2 = 1;
    }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (stackAmount >= 1) {
            amount2 ++;
        }
        updateDescription();
    }

    @Override
    public void atEndOfRound() {
        addToTop(new ApplyPowerAction(owner, owner, new DexterityPower(owner, -amount2)));
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        addToTop(new GainBlockAction(owner, amount));
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
    }

    public AbstractPower makeCopy() {return new PocketDimensionPower(owner, amount);}
}
