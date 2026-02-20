package HegemonMod.powers.buff;

import HegemonMod.patches.ArtifactPatch;
import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CompositePower extends BasePower implements ArtifactPatch.ArtifactLostSubscriber {
    public static final String POWER_ID = ("HegemonMod:" + CompositePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public CompositePower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {return new CompositePower(owner, amount);}

    @Override
    public void receiveArtifactLost(AbstractCreature owner) {
        addToTop(new DrawCardAction(amount));
    }
}
