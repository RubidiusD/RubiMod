package rubimod.powers.buff;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import rubimod.powers.BasePower;

import static rubimod.RubiMod.makeID;

public class LikeBloodPower extends BasePower {
    public static final String POWER_ID = makeID(LikeBloodPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public LikeBloodPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);

        if (owner.hasPower(ArtifactPower.POWER_ID) && owner.getPower(ArtifactPower.POWER_ID).amount > 0) {
            addToTop(new GainBlockAction(owner, amount));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
