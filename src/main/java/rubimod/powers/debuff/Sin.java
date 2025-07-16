package rubimod.powers.debuff;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import rubimod.powers.BasePower;
import rubimod.relics.PaperUmbrella;

import static rubimod.RubiMod.makeID;

public class Sin extends BasePower {
    public static final String POWER_ID = makeID(Sin.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public Sin(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    public void updateDescription() {
        if (!owner.isPlayer && AbstractDungeon.player.hasRelic(PaperUmbrella.ID))
            this.description = DESCRIPTIONS[0] + amount * 15 + DESCRIPTIONS[1];
        else
            this.description = DESCRIPTIONS[0] + amount + "0" + DESCRIPTIONS[1];
    }
}
