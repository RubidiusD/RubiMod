package rubimod.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rubimod.powers.debuff.Sin;
import rubimod.relics.PaperUmbrella;

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
        int new_damage = info.base;
        if (target.hasPower(Sin.POWER_ID))
        {
            float sin_potency = 0.1f;
            if (info.owner.isPlayer && AbstractDungeon.player.hasRelic(PaperUmbrella.ID)) {
                sin_potency = 0.15f;
                AbstractDungeon.player.getRelic(PaperUmbrella.ID).flash();
            }
            System.out.println("At potency " + sin_potency + ".");

            new_damage = MathUtils.floor(
                (
                    (float) this.info.base
                ) * (
                    1.0f + (
                        (
                            (float) target.getPower(Sin.POWER_ID).amount
                        ) * sin_potency
                    )
                )
            ); // apply sin and round down

            if (new_damage < 0)
                new_damage = 0;
        }

        addToTop(new DamageAction(target, new DamageInfo(info.owner, new_damage, DamageInfo.DamageType.THORNS), attackEffect));

        this.isDone = true;
    }
}
