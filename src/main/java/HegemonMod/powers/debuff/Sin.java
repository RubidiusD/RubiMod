package HegemonMod.powers.debuff;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import HegemonMod.powers.BasePower;
import HegemonMod.relics.PaperUmbrella;

import static HegemonMod.util.CustomTags.NECROTIC;

public class Sin extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + Sin.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public static final float Strength = 0.1f;

    public Sin(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount);     }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
        if (owner.hasPower(Necrotoxin.POWER_ID))
            owner.getPower(Necrotoxin.POWER_ID).updateDescription();
        if (owner.hasPower(LeechToxin.POWER_ID))
            owner.getPower(LeechToxin.POWER_ID).updateDescription();
    }

    @Override public float atDamageFinalReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return (card.tags.contains(NECROTIC)) ? calculateSin(damage) : damage;
    }

    @Override public void onRemove() {
        addToTop(new ApplyPowerAction(owner, owner, new LegacyofSin(owner, amount)));
    }

    public float calculateSin(float damage) {
        float sin_potency = Strength;
        if (!owner.isPlayer && AbstractDungeon.player.hasRelic(PaperUmbrella.ID)) {
            sin_potency = PaperUmbrella.Strength;
        }

        float new_damage = damage * (1.0f + amount * sin_potency);
        if (new_damage < 0) new_damage = 0;
        return new_damage;
    }

    public static int calculateSinInt(AbstractCreature target, int base) {
        int new_damage = base;
        if (target.hasPower(Sin.POWER_ID) && target.getPower(Sin.POWER_ID).amount > 0) {
            float sin_potency = Strength;
            if (!target.isPlayer && AbstractDungeon.player.hasRelic(PaperUmbrella.ID)) {
                sin_potency = PaperUmbrella.Strength;
                AbstractDungeon.player.getRelic(PaperUmbrella.ID).flash();
            }

            new_damage = MathUtils.floor(((float) base) * (1.0f + (((float) target.getPower(Sin.POWER_ID).amount) * sin_potency))); // apply sin and round

            if (new_damage < 0) new_damage = 0;
        }

        return new_damage;
    }

    @Override public void updateDescription() {
        if (!owner.isPlayer && AbstractDungeon.player.hasRelic(PaperUmbrella.ID))
            this.description = DESCRIPTIONS[0] + amount * (PaperUmbrella.Strength * 100) + DESCRIPTIONS[1];
        else
            this.description = DESCRIPTIONS[0] + amount * (Strength * 100) + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {return new Sin(owner, amount);}
}
