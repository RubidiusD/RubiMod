package rubimod.powers.debuff;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rubimod.actions.NecroticDamageAction;
import rubimod.powers.BasePower;
import rubimod.powers.buff.SinEaterPower;

import static rubimod.powers.debuff.Sin.calculateSin;

public class LeechToxin extends BasePower {
    public static final String POWER_ID = ("rubimod:" + LeechToxin.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    private static AbstractCreature source;

    public LeechToxin(AbstractCreature owner, AbstractCreature source_, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        source = source_;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        if (!owner.hasPower(Bleeding.POWER_ID)) {
            onSpecificTrigger();
        }
    }

    @Override
    public void onSpecificTrigger() {
        reducePower(1);
        if (amount == 0)
            addToTop(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        this.flash();
        updateDescription();

        addToTop(new NecroticDamageAction(owner, new DamageInfo(source, amount + 1, DamageInfo.DamageType.HP_LOSS)));
        addToTop(new ApplyPowerAction(owner, source, new Bleeding(owner, source)));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + calculateSin(owner, amount) + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {return new LeechToxin(owner, source, amount);}
}
