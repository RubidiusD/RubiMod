package HegemonMod.powers.buff;

import HegemonMod.HegemonMod;
import HegemonMod.actions.NecroticDamageAction;
import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static HegemonMod.util.CustomTags.NECROTIC;

public class ToxicPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + ToxicPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public ToxicPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        canGoNegative = true;
    }

    @Override
    public void onInitialApplication() {
        if (this.amount > 0) {
            HegemonMod.ToxicityThisTurn += this.amount;
            addToTop(new ApplyPowerAction(owner, owner, new WeakPower(owner, 1, false)));
        }
    }

    @Override public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (amount == 0) {
            addToTop(new RemoveSpecificPowerAction(owner, owner, this));
        } else {
            updateDescription();
            if (stackAmount > 0) {
                HegemonMod.ToxicityThisTurn += stackAmount;
                addToTop(new ApplyPowerAction(owner, owner, new WeakPower(owner, this.amount, false)));
            }
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
