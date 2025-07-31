package rubimod.powers.buff;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import rubimod.powers.BasePower;

import static rubimod.RubiMod.makeID;

public class SinEaterPower extends BasePower {
    public static final String POWER_ID = makeID(SinEaterPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SinEaterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void onArtifactLost(AbstractCreature owner) {
        if (owner.equals(this.owner)) {
            this.flashWithoutSound();
            addToTop(new GainBlockAction(owner, owner, this.amount));
        }
    }

    public void updateDescription() { this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]; }
}
