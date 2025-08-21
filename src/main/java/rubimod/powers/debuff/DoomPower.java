package rubimod.powers.debuff;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rubimod.powers.BasePower;



public class DoomPower extends BasePower {
    public static final String POWER_ID = ("rubimod:" + DoomPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    
    public DoomPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.amount2 = amount;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() { // Trigger the power's effect
        int totalDebuff = 0; // calculate the total amount of debuff they have
        for (AbstractPower power : owner.powers) {
            if ((power.type == PowerType.DEBUFF && !power.ID.equals(POWER_ID) && !power.canGoNegative && power.amount > 0) || (power.canGoNegative && power.amount < 0)) {
                totalDebuff += Math.abs(power.amount);
            }
        }

        if (totalDebuff == 0) { // if they have none
            this.amount2 += amount * 2; // just increase how much they'll gain later
            updateDescription();
            this.flash();
            return;
        }

        int overflow = this.amount2 / totalDebuff;
        int amount_to_distribute = this.amount2 % totalDebuff;

        for (AbstractPower power : owner.powers) {
            if ((power.type == PowerType.DEBUFF && !power.ID.equals(POWER_ID) && !power.canGoNegative  && power.amount > 0) || (power.canGoNegative && power.amount < 0)) {
                float bias = ((float) amount_to_distribute * Math.abs(power.amount)) / totalDebuff;
                int allocation = MathUtils.floor(bias);
                if (Math.random() < bias - allocation) {
                    allocation ++;
                }
                allocation += overflow * Math.abs(power.amount);

                if (allocation != 0) {
                    power.stackPower((power.canGoNegative) ? -allocation : allocation);
                    power.updateDescription();

                    amount_to_distribute -= allocation;
                    totalDebuff -= allocation;
                }
            }
        }

        this.amount2 += amount + amount_to_distribute;
        updateDescription();
        this.flash();
    }

    @Override
    public void stackPower(int stackAmount) { // on gaining an additional instance
        super.stackPower(stackAmount);
        this.amount2 += stackAmount; // also immediately increase rate of gain
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
