package rubimod.powers.debuff;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rubimod.powers.BasePower;

import static rubimod.RubiMod.makeID;

public class DoomPower extends BasePower {
    public static final String POWER_ID = makeID(DoomPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    public int debuffGainRate;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public DoomPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        debuffGainRate = amount;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() { // Trigger the power's effect
        super.atStartOfTurn();

        int totalDebuff = 0; // calculate the total amount of debuff they have
        for (AbstractPower power : owner.powers)
        {
            if ((power.type == PowerType.DEBUFF && !power.ID.equals(POWER_ID) && power.amount > 0) || (power.canGoNegative && power.amount < 0))
            {
                totalDebuff += Math.abs(power.amount);
            }
        }

        if (totalDebuff == 0) { // if they have none
            debuffGainRate += amount * 2; // just increase how much they'll gain later
            updateDescription();
            this.flash();
            return;
        }

        int overflow = debuffGainRate / totalDebuff;
        int amount_to_distribute = debuffGainRate % totalDebuff;

        for (AbstractPower power : owner.powers)
        {
            if ((power.type == PowerType.DEBUFF && !power.ID.equals(POWER_ID)) || (power.canGoNegative && power.amount < 0))
            {
                float bias = ((float) amount_to_distribute * Math.abs(power.amount)) / totalDebuff;
                int allocation = MathUtils.floor(bias);
                if (Math.random() < bias - allocation)
                    allocation ++;
                allocation += overflow * Math.abs(power.amount);

                if (power.canGoNegative && power.amount < 0) {
                    power.stackPower(-allocation);
                } else {
                    power.stackPower(allocation);
                }

                amount_to_distribute -= allocation;
                totalDebuff -= allocation;
            }
        }

        debuffGainRate += amount + amount_to_distribute;
        updateDescription();
        this.flash();
    }

    @Override
    public void stackPower(int stackAmount) { // on gaining an additional instance
        super.stackPower(stackAmount);
        this.debuffGainRate += stackAmount; // also immediately increase rate of gain
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + debuffGainRate + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
