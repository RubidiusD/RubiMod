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

    public Harbinger(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        amount2 = amount;
        updateDescription();
    }
    
    public Harbinger(AbstractCreature owner, int amount, int amount2) {
        this(owner, amount);
        this.amount2 = amount2;
    }

    @Override
    public void atStartOfTurn()
    {
        this.flashWithoutSound();
        this.amount2 += this.amount;
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

        while (this.amount2 > 0 && !debuffs.isEmpty()) { // while there are debuffs to transfer, and energy to do so with
            AbstractPower debuff = debuffs.remove((int) (Math.random() * debuffs.size())); // remove a random debuff from the list
            if (debuff.canGoNegative)
                this.amount2 += debuff.amount;
            else if (debuff.amount < 0)
                this.amount2--;
            else this.amount2 -= debuff.amount; // reduce the energy appropriately

            if (this.amount2 < 0) // reduce amount transferred if insufficient energy
                debuff.amount += (debuff.amount < 0) ? -this.amount2 : this.amount2;
            addToTop(new ApplyPowerToRandomEnemyAction(owner, debuff, debuff.amount, true)); // apply power
        }
        if (this.amount2 > 0) // if leftover energy, use to transfer self
            amount += this.amount2;
        while (amount > 0) { // transfer self
            addToTop(new ApplyPowerToRandomEnemyAction(owner, new Harbinger(null, 1), 1, true));
            amount --;
        }
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

    public AbstractPower makeCopy() {return new Harbinger(owner, amount, amount2);}
}
