package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AbsolutionAction extends AbstractGameAction {
    private boolean heals = false;

    public AbsolutionAction(AbstractCreature target, AbstractCreature source, int should_heal) {
        super();
        this.target = target;
        this.source = source;
        this.actionType = ActionType.REDUCE_POWER;
        this.attackEffect = AttackEffect.POISON;

        if (should_heal != 0)
            heals = true;
    }

    @Override
    public void update() {
        if (heals)
        {
            int total_debuff = 0;

            for (AbstractPower power : this.target.powers)
            {
                if ((power.type == AbstractPower.PowerType.DEBUFF) || (power.canGoNegative && power.amount < 0))
                {
                    total_debuff += Math.abs(power.amount);
                }
            }

            addToTop(new HealAction(source, target, total_debuff));
        }

        addToTop(new RemoveDebuffsAction(target));

        this.isDone = true;
    }
}
