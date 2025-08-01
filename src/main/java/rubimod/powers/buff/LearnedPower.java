package rubimod.powers.buff;

import com.megacrit.cardcrawl.core.AbstractCreature;
import rubimod.powers.BasePower;



public class LearnedPower extends BasePower {
    public static final String POWER_ID = ("rubimod:" + LearnedPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.

    public LearnedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        amount = -1;
        updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
