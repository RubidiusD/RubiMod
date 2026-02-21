package HegemonMod.powers.debuff;

import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class GiantSlayerPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + GiantSlayerPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public GiantSlayerPower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public int onHeal(int healAmount) {
        addToTop(new ApplyPowerAction(player, owner, new StrengthPower(player, amount)));
        return healAmount;
    }

    @Override public void onRemove() {
        addToTop(new ApplyPowerAction(owner, owner, makeCopy()));
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {return new GiantSlayerPower(owner, amount);}
}
