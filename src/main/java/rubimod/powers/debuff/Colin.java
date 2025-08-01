package rubimod.powers.debuff;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import rubimod.powers.BasePower;



public class Colin extends BasePower {
    public static final String POWER_ID = ("rubimod:" + Colin.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

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
        reducePower(1);
        if (amount == 0)
            addToTop(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        this.flash();
        updateDescription();

        addToTop(new ApplyPowerAction(owner, source, new Bleeding(owner, source)));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToTop(new ApplyPowerAction(owner, source, new VulnerablePower(owner,1, !source.isPlayer)));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
