package rubimod.powers.buff;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import rubimod.actions.ApplyNecrotoxinAction;
import rubimod.powers.BasePower;



public class UmbralVenom extends BasePower {
    public static final String POWER_ID = ("rubimod:" + UmbralVenom.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public UmbralVenom(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type != DamageInfo.DamageType.HP_LOSS)
        {
            addToTop(new ApplyNecrotoxinAction(target, owner, amount));
            flashWithoutSound();
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        owner.powers.remove(this);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
