package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class MercyAction extends AbstractGameAction {
    public MercyAction(AbstractCreature target, AbstractCreature source) {
        super();
        this.target = target;
        this.source = source;
        this.actionType = ActionType.REDUCE_POWER;
        this.attackEffect = AttackEffect.POISON;
    }

    @Override
    public void update() {
        int total_debuff = 0;

        for (AbstractPower power : this.target.powers)
        {
            if ((power.type == AbstractPower.PowerType.DEBUFF) || (power.canGoNegative && power.amount < 0))
            {
                total_debuff += Math.abs(power.amount);
            }
        }

        addToTop(new ApplyPowerAction(target, source, new StrengthPower(target, -total_debuff)));
        addToTop(new ApplyPowerAction(target, source, new GainStrengthPower(target, total_debuff / 2)));
        addToTop(new RemoveDebuffsAction(target));

        this.isDone = true;
    }
}
