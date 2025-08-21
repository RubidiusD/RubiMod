package rubimod.powers.buff;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import rubimod.actions.NecroticDamageAction;
import rubimod.powers.BasePower;

public class ShadowHand extends BasePower {
    public static final String POWER_ID = ("rubimod:" + ShadowHand.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ShadowHand(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type == DamageInfo.DamageType.NORMAL)
            addToTop(new NecroticDamageAction(target, new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), false));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
