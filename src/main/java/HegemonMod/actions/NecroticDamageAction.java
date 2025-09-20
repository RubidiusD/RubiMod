package HegemonMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static HegemonMod.powers.debuff.Sin.calculateSinInt;

public class NecroticDamageAction extends AbstractGameAction {
    public NecroticDamageAction(AbstractCreature target, AbstractCreature source, int amount, AttackEffect effect)
    {
        this.attackEffect = effect;
        this.source = source;
        this.target = target;
        this.amount = amount;
        this.actionType = ActionType.DAMAGE;
    }

    public NecroticDamageAction(AbstractCreature target, AbstractCreature source, int amount)
    {
        this(target, source, amount, AttackEffect.NONE);
    }

    public void update()
    {
        addToTop(new DamageAction(target, new DamageInfo(source, calculateSinInt(target, amount), DamageInfo.DamageType.THORNS), attackEffect));

        this.isDone = true;
    }
}
