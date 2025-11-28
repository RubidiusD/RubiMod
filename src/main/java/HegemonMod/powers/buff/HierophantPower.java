package HegemonMod.powers.buff;

import HegemonMod.cards.attacks.Punition;
import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HierophantPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + HierophantPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HierophantPower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public void atStartOfTurn() {
        this.flash();
        addToBot(new MakeTempCardInHandAction(new Punition(), amount));
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() { return new HierophantPower(owner, amount);}
}
