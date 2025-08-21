package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import rubimod.powers.buff.ShadowHand;

import static rubimod.powers.debuff.Sin.calculateSin;

public class NecroticDamageAction extends AbstractGameAction {
    private final DamageInfo info;

    public NecroticDamageAction(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect, boolean shouldApplyBonus)
    {
        this.attackEffect = effect;
        if (shouldApplyBonus && info.owner != null && info.owner.hasPower(ShadowHand.POWER_ID)) {
            this.info = new DamageInfo(info.owner, info.base + info.owner.getPower(ShadowHand.POWER_ID).amount, DamageInfo.DamageType.THORNS);
        }
        else {
            this.info = info;
        }
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
    }

    public NecroticDamageAction(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect)
    {
        this(target, info, effect, true);
    }

    public NecroticDamageAction(AbstractCreature target, DamageInfo info)
    {
        this(target, info, AttackEffect.NONE, true);
    }

    public NecroticDamageAction(AbstractCreature target, DamageInfo info, boolean shouldApplyBonus)
    {
        this(target, info, AttackEffect.NONE, shouldApplyBonus);
    }

    public void update()
    {
        addToTop(new DamageAction(target, new DamageInfo(info.owner, calculateSin(target, info.base), DamageInfo.DamageType.THORNS), attackEffect));

        this.isDone = true;
    }
}
