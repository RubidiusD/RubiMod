package HegemonMod.powers.buff;

import HegemonMod.actions.NecroticDamageAction;
import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static HegemonMod.util.CustomTags.NECROTIC;

public class ToxicPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + ToxicPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public ToxicPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        canGoNegative = true;
    }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (amount == 0) {
            addToTop(new RemoveSpecificPowerAction(owner, owner, this));
        } else {
            updateDescription();
        }
    }

    @Override public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return (amount < 0 && card.hasTag(NECROTIC)) ? damage + amount : damage;
    }

    @Override public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (amount > 0 && info.type == DamageInfo.DamageType.NORMAL)
        {
            addToTop(new NecroticDamageAction(target, info.owner, this.amount, AbstractGameAction.AttackEffect.POISON));
            flashWithoutSound();
        }
    }

    @Override public void atEndOfTurn(boolean isPlayer) {
        if (this.amount > 0) {
            this.amount --;
            if (this.amount == 0) {
                addToTop(new RemoveSpecificPowerAction(owner, owner, this));
            } else {
                updateDescription();
                flashWithoutSound();
            }
        }
    }

    @Override public void updateDescription() {
        if (amount < 0) {
            this.description = DESCRIPTIONS[2] + -amount + DESCRIPTIONS[3];
        } else {
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
    }

    public AbstractPower makeCopy() {return new ToxicPower(owner, amount);}
}
