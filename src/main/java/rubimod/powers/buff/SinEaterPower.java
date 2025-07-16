package rubimod.powers.buff;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.green.DodgeAndRoll;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import rubimod.powers.BasePower;

import static rubimod.RubiMod.makeID;

public class SinEaterPower extends BasePower {
    public static final String POWER_ID = makeID(SinEaterPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public SinEaterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    // new
    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == PowerType.DEBUFF && !power.ID.equals("Shackled") && target == this.owner && target.hasPower("Artifact") && target.getPower(ArtifactPower.POWER_ID).amount != 0) {
            this.flash();
            addToTop(new GainBlockAction(owner, owner, this.amount));
            addToTop(new ApplyPowerAction(owner, owner, new NextTurnBlockPower(owner, this.amount)));
        }
    }

    // old
//    @Override
//    public void onSpecificTrigger() {
//        super.onSpecificTrigger();
//        flash();
//        addToTop(new GainBlockAction(owner, owner, this.amount));
//    }

    public void updateDescription() { this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]; }
}
