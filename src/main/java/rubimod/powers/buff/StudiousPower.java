package rubimod.powers.buff;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import rubimod.actions.SkillBookAction;
import rubimod.powers.BasePower;

import static rubimod.RubiMod.makeID;
import static rubimod.character.Hegemon.Meta.LIBRARY_COLOR;

public class StudiousPower extends BasePower {
    public static final String POWER_ID = makeID(StudiousPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public StudiousPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();

        addToBot(new SkillBookAction(AbstractCard.CardRarity.UNCOMMON, LIBRARY_COLOR, SkillBookAction.SingleUse.TRUE));
        this.flashWithoutSound();
    }

    public void updateDescription() {
        if (amount == 1)
            this.description = DESCRIPTIONS[0];
        else
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
