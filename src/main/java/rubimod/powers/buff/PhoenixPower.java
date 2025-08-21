package rubimod.powers.buff;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rubimod.powers.BasePower;



public class PhoenixPower extends BasePower {
    public static final String POWER_ID = ("rubimod:" + PhoenixPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public PhoenixPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.amount2 = amount;
        updateDescription();
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        int totalBuff = 0; // calculate the total amount of buff you have
        for (AbstractPower power : owner.powers) {
            if (power.amount > 0 && ((power.type == PowerType.BUFF && !power.ID.equals(POWER_ID)) || (power.canGoNegative))) {
                totalBuff += power.amount;
            }
        }

        if (totalBuff == 0) { // if you have none
            this.amount2 += amount * 2; // just increase how much they'll gain later
            updateDescription();
            this.flash();
            return;
        }

        int overflow = this.amount2 / totalBuff;
        int amount_to_distribute = this.amount2 % totalBuff;

        for (AbstractPower power : owner.powers) {
            if (power.amount > 0 && ((power.type == PowerType.BUFF && !power.ID.equals(POWER_ID)) || (power.canGoNegative))) {
                float bias = ((float) amount_to_distribute * power.amount) / totalBuff;
                int allocation = MathUtils.floor(bias);
                if (Math.random() < bias - allocation) {
                    allocation ++;
                }
                allocation += overflow * power.amount;

                if (allocation != 0) {
                    power.stackPower(allocation);
                    power.updateDescription();
                    amount_to_distribute -= allocation;
                    totalBuff -= allocation;
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
        updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
