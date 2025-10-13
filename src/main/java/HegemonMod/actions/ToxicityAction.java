package HegemonMod.actions;

import HegemonMod.powers.buff.ToxicPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class ToxicityAction extends AbstractGameAction {
    public ToxicityAction(AbstractCreature target, int amount) {
        this.amount = amount;
        this.target = target;
    }

    @Override public void update() {
        if (amount < 0 && target.hasPower(ArtifactPower.POWER_ID)) {
            target.getPower(ArtifactPower.POWER_ID).onSpecificTrigger();
        } else {
            addToTop(new ApplyPowerAction(this.target, this.target, new ToxicPower(this.target, this.amount)));
        }

        this.isDone = true;
    }
}
