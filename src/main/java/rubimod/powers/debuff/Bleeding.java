package rubimod.powers.debuff;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rubimod.powers.BasePower;



public class Bleeding extends BasePower {
    public static final String POWER_ID = ("rubimod:" + Bleeding.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    private static AbstractCreature source;

    public Bleeding(AbstractCreature owner, AbstractCreature source_) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
        source = source_;
    }

    @Override
    public void stackPower(int stackAmount) {
        amount = -1;
    }

    @Override
    public int onHeal(int healAmount) {
        if (healAmount > 0) { addToTop(new RemoveSpecificPowerAction(owner, source, POWER_ID)); }
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

}
