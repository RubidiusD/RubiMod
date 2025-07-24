package rubimod.powers.buff;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import rubimod.powers.BasePower;

import static rubimod.RubiMod.makeID;

public class VaccinePower extends BasePower {
    public static final String POWER_ID = makeID(VaccinePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public VaccinePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);

        if (target.equals(owner) && ( (power.canGoNegative) ? (power.amount < 0) : (power.type == PowerType.DEBUFF)) && (!owner.hasPower(ArtifactPower.POWER_ID) || owner.getPower(ArtifactPower.POWER_ID).amount < 1))
            addToTop(new ApplyPowerAction(owner, owner, new ArtifactPower(owner, amount)));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
