package rubimod.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rubimod.powers.debuff.Sin;
import rubimod.relics.PrayerBeads;

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
        if (target.hasPower(Sin.POWER_ID))
        {
            float sin_potency = 0.1f;
            if (info.owner.isPlayer && AbstractDungeon.player.hasRelic(PrayerBeads.ID)) {
                sin_potency = 0.15f;
                AbstractDungeon.player.getRelic(PrayerBeads.ID).flash();
            }

            this.info.base = MathUtils.floor(((float) this.info.base) * (1.0f + ((float) target.getPower(Sin.POWER_ID).amount) * sin_potency)); // apply sin and round down

            if (this.info.base < 0)
                this.info.base = 0;
        }

        addToTop(new DamageAction(target, info, attackEffect));

        this.isDone = true;
    }
}
