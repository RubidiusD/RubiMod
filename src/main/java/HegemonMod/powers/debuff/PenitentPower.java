package HegemonMod.powers.debuff;

import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PenitentPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + PenitentPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    private static final float STRENGTH = 0.15f;

    public PenitentPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * (1.0f + STRENGTH * (float) amount);
        } else {
            return damage;
        }
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + ((int)(STRENGTH * 100) * amount) + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {return new PenitentPower(owner, amount);}
}