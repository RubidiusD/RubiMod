package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import rubimod.powers.debuff.NecroToxin;

public class ApplyNecroToxinAction extends AbstractGameAction {
    AbstractCreature target;
    AbstractCreature source;
    int amount;

    public ApplyNecroToxinAction(AbstractCreature target, AbstractCreature source, int amount)
    {
        this.actionType = ActionType.POWER;
        this.target = target;
        this.source = source;
        this.amount = amount;
    }

    public void update()
    {
        addToTop(new ApplyPowerAction(target, source, new NecroToxin(target, source, amount)));
        this.isDone = true;
    }
}
