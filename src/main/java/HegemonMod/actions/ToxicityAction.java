package HegemonMod.actions;

import HegemonMod.powers.buff.ToxicPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ToxicityAction extends AbstractGameAction {
    public ToxicityAction(AbstractCreature target, int amount) {
        this.amount = amount;
        this.target = target;
    }

    @Override public void update() {
        addToTop(new ApplyPowerAction(this.target, this.target, new ToxicPower(this.target, this.amount)));

        this.isDone = true;
    }
}
