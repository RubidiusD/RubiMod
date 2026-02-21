package HegemonMod.powers.buff;

import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawReductionPower;

public class EfficiencyPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + EfficiencyPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public EfficiencyPower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.costForTurn == 0) {
            addToBot(new DrawCardAction(amount));
            addToBot(new ApplyPowerAction(owner, owner, new DrawReductionPower(owner, amount)));
        }
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    public AbstractPower makeCopy() {return new EfficiencyPower(owner, amount);}
}
