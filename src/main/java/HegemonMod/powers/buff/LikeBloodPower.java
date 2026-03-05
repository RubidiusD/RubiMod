package HegemonMod.powers.buff;

import HegemonMod.powers.BasePower;
import HegemonMod.powers.debuff.PenitentPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class LikeBloodPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + LikeBloodPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public LikeBloodPower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount);     }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public void atEndOfTurn(boolean isPlayer) {
        if (owner.hasPower(ArtifactPower.POWER_ID) && owner.getPower(ArtifactPower.POWER_ID).amount > 0) {
            addToTop(new GainBlockAction(owner, amount));
        }
        else {
            addToTop(new ApplyPowerAction(owner, owner, new PenitentPower(owner, 1)));
        }
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {return new LikeBloodPower(owner, amount);}
}
