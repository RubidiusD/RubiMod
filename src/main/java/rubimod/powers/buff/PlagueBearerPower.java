package rubimod.powers.buff;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import rubimod.cards.skills.Vector;
import rubimod.powers.BasePower;


public class PlagueBearerPower extends BasePower {
    public static final String POWER_ID = ("rubimod:" + PlagueBearerPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public PlagueBearerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.flash();
        addToBot(new MakeTempCardInDrawPileAction(new Vector(), amount, true, true));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
