package HegemonMod.powers.buff;

import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class UndeathPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + UndeathPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public UndeathPower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        addToTop(new DamageAction(owner, new DamageInfo(owner, amount, DamageInfo.DamageType.HP_LOSS)));
        addToTop(new ApplyPowerAction(owner, owner, new ArtifactPower(owner, amount)));
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    public AbstractPower makeCopy() { return new UndeathPower(owner, amount); }
}
