package rubimod.powers.buff;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.RegenPower;
import rubimod.powers.BasePower;

import static rubimod.RubiMod.makeID;

public class BlessingsBounty extends BasePower {
    public static final String POWER_ID = makeID(BlessingsBounty.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public BlessingsBounty(AbstractCreature owner, int amount) {
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
            this.flash();
            addToTop(new ApplyPowerAction(owner, owner, new RegenPower(owner, amount)));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
