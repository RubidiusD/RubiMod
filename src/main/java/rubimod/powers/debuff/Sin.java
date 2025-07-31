package rubimod.powers.debuff;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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

    public Sin(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
        if (owner.hasPower(Necrotoxin.POWER_ID))
            owner.getPower(Necrotoxin.POWER_ID).updateDescription();
    }

    @Override
    public void onRemove() {
        addToTop(new ApplyPowerAction(owner, owner, new LegacyofSin(owner, amount)));
    }

    public static int calculateSin(AbstractCreature target, int base)
    {
        int new_damage = base;
        if (target.hasPower(Sin.POWER_ID) && target.getPower(Sin.POWER_ID).amount > 0)
        {
            float sin_potency = 0.1f;
            if (!target.isPlayer
                    && AbstractDungeon.player.hasRelic(PaperUmbrella.ID)
            ) {
                sin_potency = 0.15f;
                AbstractDungeon.player.getRelic(PaperUmbrella.ID).flash();
            }

            new_damage = MathUtils.floor(
                    (
                            (float) base
                    ) * (
                            1.0f + (
                                    (
                                            (float) target.getPower(Sin.POWER_ID).amount
                                    ) * sin_potency
                            )
                    ) + 0.5f
            ); // apply sin and round

            if (new_damage < 0)
                new_damage = 0;
        }

        return new_damage;
    }

    public void updateDescription() {
        if (!owner.isPlayer && AbstractDungeon.player.hasRelic(PaperUmbrella.ID))
            this.description = DESCRIPTIONS[0] + amount * 15 + DESCRIPTIONS[1];
        else
            this.description = DESCRIPTIONS[0] + amount + "0" + DESCRIPTIONS[1];
    }
}
