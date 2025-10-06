package HegemonMod.actions;

import HegemonMod.HegemonMod;
import HegemonMod.powers.buff.ToxicPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ToxicityAction extends AbstractGameAction {
    public ToxicityAction(AbstractCreature target, int amount) {
        this.amount = amount;
        this.target = target;
    }

    @Override public void update() {
        addToTop(new ApplyPowerAction(this.target, this.target, new ToxicPower(this.target, this.amount)));
        addToTop(new ApplyPowerAction(this.target, this.target, new WeakPower(this.target, this.amount, false)));
        HegemonMod.ToxicityThisTurn += this.amount;

        this.isDone = true;
    }
}
