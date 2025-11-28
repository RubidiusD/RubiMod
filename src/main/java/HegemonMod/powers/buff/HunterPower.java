package HegemonMod.powers.buff;

import HegemonMod.actions.ToxicityAction;
import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HunterPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + HunterPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HunterPower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public void atStartOfTurn() {
        this.flash();
        addToTop(new ToxicityAction(owner, amount));
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() { return new HunterPower(owner, amount);}
}
