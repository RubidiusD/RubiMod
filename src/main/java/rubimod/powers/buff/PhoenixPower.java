package rubimod.powers.buff;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rubimod.powers.BasePower;

import static rubimod.RubiMod.makeID;

public class PhoenixPower extends BasePower {
    public static final String POWER_ID = makeID(PhoenixPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public int buffGainRate;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public PhoenixPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        buffGainRate = amount;
        updateDescription();
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        super.atEndOfTurnPreEndTurnCards(isPlayer);

        int totalBuff = 0; // calculate the total amount of buff you have
        for (AbstractPower power : owner.powers)
        {
            if (power.amount > 0 && ((power.type == PowerType.BUFF && !power.ID.equals(POWER_ID)) || (power.canGoNegative)))
            {
                totalBuff += power.amount;
            }
        }

        if (totalBuff == 0) { // if you have none
            buffGainRate += amount * 2; // just increase how much they'll gain later
            updateDescription();
            this.flash();
            return;
        }

        int overflow = buffGainRate / totalBuff;
        int amount_to_distribute = buffGainRate % totalBuff;

        for (AbstractPower power : owner.powers)
        {
            if (power.amount > 0 && ((power.type == PowerType.BUFF && !power.ID.equals(POWER_ID)) || (power.canGoNegative)))
            {
                float bias = ((float) amount_to_distribute * power.amount) / totalBuff;
                int allocation = MathUtils.floor(bias);
                if (Math.random() < bias - allocation)
                    allocation ++;
                allocation += overflow * power.amount;

                if (allocation != 0)
                {
                    power.stackPower(allocation);
                    power.updateDescription();
                    amount_to_distribute -= allocation;
                    totalBuff -= allocation;
                }
            }
        }

        buffGainRate += amount + amount_to_distribute;
        updateDescription();
        this.flash();
    }

    @Override
    public void stackPower(int stackAmount) { // on gaining an additional instance
        super.stackPower(stackAmount);
        this.buffGainRate += stackAmount; // also immediately increase rate of gain
        updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + buffGainRate + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
