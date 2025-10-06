package HegemonMod.powers.debuff;

import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GentlePower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + GentlePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public GentlePower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount);     }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type == DamageInfo.DamageType.NORMAL)
        {
            addToTop(new HealAction(target, owner, amount));
            flashWithoutSound();
        }
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {return new GentlePower(owner, amount);}
}
