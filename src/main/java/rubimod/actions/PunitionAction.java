package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import rubimod.NecroticDamageInfo;
import rubimod.powers.Sin;

public class PunitionAction extends AbstractGameAction {
    AbstractCreature owner;
    AbstractCreature target;

    public PunitionAction(AbstractCreature owner, AbstractCreature target) {
        super();
        this.owner = owner;
        this.target = target;
    }

    public void update()
    {
        if (target.getPower(Sin.POWER_ID).amount == 0)
        {
            this.isDone = true;
            return;
        }
        addToTop(new DamageAction(target, new NecroticDamageInfo(owner, target.getPower(Sin.POWER_ID).amount), AttackEffect.POISON));

        this.isDone = true;
    }
}
