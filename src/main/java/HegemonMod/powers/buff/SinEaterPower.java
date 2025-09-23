package HegemonMod.powers.buff;

import HegemonMod.patches.ArtifactPatch;
import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SinEaterPower extends BasePower implements ArtifactPatch.ArtifactLostSubscriber {
    public static final String POWER_ID = ("HegemonMod:" + SinEaterPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SinEaterPower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount);     }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }
    
    @Override public void receiveArtifactLost(AbstractCreature owner) {
        if (owner.equals(this.owner)) {
            this.flashWithoutSound();
            addToTop(new GainBlockAction(owner, owner, this.amount));
        }
    }

    @Override public void updateDescription() { this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]; }

    public AbstractPower makeCopy() {return new SinEaterPower(owner, amount);}
}
