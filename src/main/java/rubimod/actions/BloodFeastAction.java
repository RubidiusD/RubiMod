package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.RegenPower;
import rubimod.powers.debuff.Bleeding;

public class BloodFeastAction extends AbstractGameAction {
    private int amount = 0;
    private int heal = 0;
    public BloodFeastAction(AbstractCreature target, AbstractCreature source, int amount, int heal) {
        super();
        this.target = target;
        this.source = source;
        this.actionType = ActionType.DEBUFF;
        this.attackEffect = AttackEffect.POISON;
        this.amount = amount;
        this.heal = heal;
    }

    @Override
    public void update() {
        if (target.hasPower(Bleeding.POWER_ID)) {
            addToTop(new ApplyPowerAction(source, source, new RegenPower(source, heal)));
            addToTop(new RemoveSpecificPowerAction(target, source, Bleeding.POWER_ID));
        }
        addToTop(new ApplyNecrotoxinAction(target, source, amount));

        this.isDone = true;
    }
}
