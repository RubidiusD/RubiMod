package HegemonMod.powers.buff;

import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class LearnedPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + LearnedPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public LearnedPower(AbstractCreature owner) { super(POWER_ID, TYPE, TURN_BASED, owner, -1);     }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public AbstractPower makeCopy() {return new LearnedPower(owner);}
}
