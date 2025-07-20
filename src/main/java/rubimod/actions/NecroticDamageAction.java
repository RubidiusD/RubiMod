package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static rubimod.powers.debuff.Sin.calculateSin;

public class NecroticDamageAction extends AbstractGameAction {
    private final DamageInfo info;

    public NecroticDamageAction(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect)
    {
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
    }

    public NecroticDamageAction(AbstractCreature target, DamageInfo info)
    {
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.NONE;
    }

    public void update()
    {
        addToTop(new DamageAction(target, new DamageInfo(info.owner, calculateSin(target, info.owner, info.base), DamageInfo.DamageType.THORNS), attackEffect));

        this.isDone = true;
    }
}
