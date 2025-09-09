package rubimod.powers.debuff;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rubimod.powers.BasePower;

public class Bleeding extends BasePower {
    public static final String POWER_ID = ("rubimod:" + Bleeding.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public Bleeding(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void stackPower(int stackAmount) {
        amount = -1;
    }

    @Override
    public int onHeal(int healAmount) {
        if (healAmount > 0) { addToTop(new RemoveSpecificPowerAction(owner, owner, POWER_ID)); }
        return 0;
    }

    @Override
    public void onRemove() {
        if (owner.hasPower(LeechToxin.POWER_ID)) {owner.getPower(LeechToxin.POWER_ID).onSpecificTrigger();}
        this.flash();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public AbstractPower makeCopy() {return new Bleeding(owner);}
}
