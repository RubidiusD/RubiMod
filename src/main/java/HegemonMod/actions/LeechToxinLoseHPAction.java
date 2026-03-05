package HegemonMod.actions;

import HegemonMod.powers.debuff.LeechToxin;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static HegemonMod.powers.debuff.Sin.calculateSinInt;

public class LeechToxinLoseHPAction extends PoisonLoseHpAction {
    public LeechToxinLoseHPAction(AbstractCreature target, AbstractCreature source, int amount, AttackEffect effect) {
        super(target, source, amount, effect);
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            this.isDone = true;
            return;
        }
        if (this.duration == 0.33F && this.target.currentHealth > 0)
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
        tickDuration();
        if (this.isDone) {
            if (this.target.currentHealth > 0) {
                this.target.tint.color = Color.CORAL.cpy();
                this.target.tint.changeColor(Color.WHITE.cpy());
                this.target.damage(new DamageInfo(this.source, calculateSinInt(target, amount), DamageInfo.DamageType.HP_LOSS));
                AbstractPower p = target.getPower(LeechToxin.POWER_ID);
                p.reducePower(1);
                if (p.amount == 0)
                    addToTop(new RemoveSpecificPowerAction(target, target, LeechToxin.POWER_ID));
                else
                    p.updateDescription();
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
            addToTop(new WaitAction(0.1F));
        }
    }
}
