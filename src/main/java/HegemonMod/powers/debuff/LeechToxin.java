package HegemonMod.powers.debuff;

import HegemonMod.actions.LeechToxinLoseHPAction;
import HegemonMod.powers.BasePower;
import HegemonMod.powers.buff.RecoveryPower;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static HegemonMod.powers.debuff.Sin.calculateSinInt;

public class LeechToxin extends BasePower implements HealthBarRenderPower {
    public static final String POWER_ID = ("HegemonMod:" + LeechToxin.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public LeechToxin(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.source = source;
    }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public int onHeal(int healAmount) {
        onSpecificTrigger();
        return 0;
    }

    @Override public void onSpecificTrigger() {
        reducePower(1);
        if (amount == 0)
            addToTop(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        this.flash();
        updateDescription();

        addToTop(new LeechToxinLoseHPAction(owner, source, amount + 1, AbstractGameAction.AttackEffect.POISON));
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + calculateSinInt(owner, amount) + DESCRIPTIONS[2];
    }

    @Override public int getHealthBarAmount() { return (owner.hasPower(RecoveryPower.POWER_ID)) ? calculateSinInt(owner, this.amount) : 0; }

    @Override public Color getColor() { return CardHelper.getColor(150, 200, 100); }

    @Override public AbstractPower makeCopy() {return new LeechToxin(owner, source, amount);}
}
