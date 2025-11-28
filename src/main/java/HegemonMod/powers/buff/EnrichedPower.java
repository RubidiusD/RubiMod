package HegemonMod.powers.buff;

import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EnrichedPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + EnrichedPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public EnrichedPower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {return new EnrichedPower(owner, amount);}
}
