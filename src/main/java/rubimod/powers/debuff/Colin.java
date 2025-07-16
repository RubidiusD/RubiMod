package rubimod.powers.debuff;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import rubimod.powers.BasePower;

import static rubimod.RubiMod.makeID;

public class Colin extends BasePower {
    public static final String POWER_ID = makeID(Colin.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    private static AbstractCreature source;

    public Colin(AbstractCreature owner, AbstractCreature source_, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        source = source_;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();

        reducePower(1);
        if (amount == 0)
            addToTop(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        this.flash();
        updateDescription();

        addToTop(new ApplyPowerAction(owner, source, new Bleeding(owner, source)));
        addToTop(new ApplyPowerAction(owner, source, new VulnerablePower(owner,1, !source.isPlayer)));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
