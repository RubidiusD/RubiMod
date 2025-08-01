package rubimod.powers.debuff;

import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rubimod.powers.BasePower;

import java.util.ArrayList;



public class Harbinger extends BasePower {
    public static final String POWER_ID = ("rubimod:" + Harbinger.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    public int transferEnergy;

    public Harbinger(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        transferEnergy = amount;
        updateDescription();
    }

    @Override
    public void atStartOfTurn()
    {
        this.flashWithoutSound();
        transferEnergy += this.amount;
        updateDescription();
    }

    @Override
    public void onDeath() { // Trigger the power's effect
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) // check there are other monsters to transfer to
            return;
        ArrayList<AbstractPower> debuffs = new ArrayList<>(owner.powers); // Get a copy of the owner's debuffs
        for (int index = 0; index < debuffs.size(); index++) { // Remove the buffs (and itself)
            AbstractPower debuff = debuffs.get(index);
            if (debuff.canGoNegative ? debuff.amount > 0 : debuff.type == PowerType.BUFF || debuff.equals(this))
            {
                debuffs.remove(debuff);
                index --;
            }
        }

        while (transferEnergy > 0 && !debuffs.isEmpty()) { // while there are debuffs to transfer, and energy to do so with
            AbstractPower debuff = debuffs.remove((int) (Math.random() * debuffs.size())); // remove a random debuff from the list
            if (debuff.canGoNegative)
                transferEnergy += debuff.amount;
            else if (debuff.amount < 0)
                transferEnergy--;
            else transferEnergy -= debuff.amount; // reduce the energy appropriately

            if (transferEnergy < 0) // reduce amount transferred if insufficient energy
                debuff.amount += (debuff.amount < 0) ? -transferEnergy : transferEnergy;
            addToTop(new ApplyPowerToRandomEnemyAction(owner, debuff, debuff.amount, true)); // apply power
        }
        if (transferEnergy > 0) // if leftover energy, use to transfer self
            amount += transferEnergy;
        while (amount > 0) { // transfer self
            addToTop(new ApplyPowerToRandomEnemyAction(owner, new Harbinger(null, 1), 1, true));
            amount --;
        }
    }

    @Override
    public void stackPower(int stackAmount) { // on gaining an additional instance
        super.stackPower(stackAmount);
        this.transferEnergy += stackAmount; // also immediately increase rate of gain
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + transferEnergy + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
