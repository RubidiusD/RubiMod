package rubimod.powers.buff;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rubimod.powers.BasePower;

public class TacticalMapPower extends BasePower {
    public static final String POWER_ID = ("rubimod:" + TacticalMapPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public TacticalMapPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.amount = -1;
    }

    @Override
    public void onExhaust(AbstractCard card) {
        super.onExhaust(card);

        addToTop(new DiscardAction(owner, owner, amount, false));
        addToTop(new DrawCardAction(owner, amount));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public AbstractPower makeCopy() {return new TacticalMapPower(owner);}
}
