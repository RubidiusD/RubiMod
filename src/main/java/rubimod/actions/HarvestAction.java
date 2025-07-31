package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;

public class HarvestAction extends AbstractGameAction {
    private final int amount;
    public HarvestAction(AbstractCreature target, AbstractCreature source, int amount) {
        super();
        this.target = target;
        this.source = source;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.NONE;
        this.amount = amount;
    }

    @Override
    public void update() {
        this.target.damage(new DamageInfo(this.source, this.amount, DamageInfo.DamageType.THORNS));
        if (this.target.lastDamageTaken != 0 && (this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower(MinionPower.POWER_ID)) {
            addToTop(new HealAction(source, source, target.lastDamageTaken));

            for(int j = 0; j < target.lastDamageTaken / 2 && j < 10; ++j) {
                this.addToBot(new VFXAction(new FlyingOrbEffect(target.hb.cX, target.hb.cY)));
            }
        }

        this.isDone = true;
    }
}
