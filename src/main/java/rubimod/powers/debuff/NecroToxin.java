package rubimod.powers.debuff;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import rubimod.actions.NecrotoxinLoseHPAction;
import rubimod.powers.BasePower;


import static rubimod.powers.debuff.Sin.calculateSin;

public class Necrotoxin extends BasePower implements HealthBarRenderPower {
    public static final String POWER_ID = ("rubimod:" + Necrotoxin.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;
    private final AbstractCreature source;

    public Necrotoxin(AbstractCreature owner, AbstractCreature source, int amount) {
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
            addToTop(new NecrotoxinLoseHPAction(owner, source, amount, AbstractGameAction.AttackEffect.POISON));

            reducePower(1);
            if (amount == 0)
                addToTop(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
            this.updateDescription();
            this.flashWithoutSound();
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (owner.hasPower(Sin.POWER_ID))
            this.description += "#g" + calculateSin(owner, amount) + DESCRIPTIONS[1] + calculateSin(owner, amount * (amount + 1) / 2);
        else
            this.description += "#b" + amount + DESCRIPTIONS[1] + amount * (amount + 1) / 2;
        this.description += DESCRIPTIONS[2];
    }

    @Override
    public int getHealthBarAmount() {
        return calculateSin(owner, this.amount);
    }

    @Override
    public Color getColor() {
        return CardHelper.getColor(50, 25, 75);
    }
}