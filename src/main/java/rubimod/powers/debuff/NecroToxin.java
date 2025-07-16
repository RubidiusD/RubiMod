package rubimod.powers.debuff;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import rubimod.actions.NecroticDamageAction;
import rubimod.powers.BasePower;

import static rubimod.RubiMod.makeID;

public class NecroToxin extends BasePower {
    public static final String POWER_ID = makeID(NecroToxin.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;
    private final AbstractCreature source;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.

    public NecroToxin(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.source = source;
    }

    @Override
    public void stackPower(int stackAmount) {
        if (stackAmount + amount > 999)
            stackAmount = 999 - amount;
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();

        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToTop(new NecroticDamageAction(owner, new DamageInfo(source, amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));

            reducePower(1);
            if (amount == 0)
                addToTop(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
            this.updateDescription();
            this.flashWithoutSound();
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount * (amount + 1) / 2 + DESCRIPTIONS[2];
    }
}