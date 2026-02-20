package HegemonMod.powers.buff;

import HegemonMod.cards.attacks.Punish;
import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DeservePower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + DeservePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public DeservePower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (5 - amount2) + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    @Override
    public void atStartOfTurn() {
        this.amount2 = 0;
        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        amount2 ++;
        if (amount2 == 5) {
            amount2 = 0;
            addToBot(new MakeTempCardInHandAction(new Punish(), amount));
        }
        updateDescription();
    }

    public AbstractPower makeCopy() {return new DeservePower(owner, amount);}
}
