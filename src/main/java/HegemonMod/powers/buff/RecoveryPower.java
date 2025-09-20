package HegemonMod.powers.buff;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import HegemonMod.powers.BasePower;

public class RecoveryPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + RecoveryPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public RecoveryPower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public void atStartOfTurn() {
        addToTop(new HealAction(owner, owner, amount));
        this.flash();
    }

    @Override public void updateDescription() { this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]; }

    public AbstractPower makeCopy() {return new RecoveryPower(owner, amount);}
}
