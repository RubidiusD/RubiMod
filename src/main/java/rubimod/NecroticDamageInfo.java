package rubimod;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rubimod.powers.debuff.Sin;
import rubimod.relics.PaperUmbrella;

public class NecroticDamageInfo extends DamageInfo { // This version of damage info is affected by Sin.
    public NecroticDamageInfo(AbstractCreature damageSource, int base) {
        super(damageSource, base, DamageType.THORNS);
    }

    public NecroticDamageInfo(AbstractCreature damageSource, int base, DamageType damageType) {
        super(damageSource, base, damageType);
    }

    @Override
    public void applyPowers(AbstractCreature owner, AbstractCreature target) {
        super.applyPowers(owner, target);

        int sin = target.getPower(Sin.POWER_ID).amount; // get the target's amount of sin
        if (sin != 0) { // if they have any
            float sin_potency = 0.1f;
            if (owner.isPlayer && AbstractDungeon.player.hasRelic(PaperUmbrella.ID)) {
                sin_potency = 0.15f;
                AbstractDungeon.player.getRelic(PaperUmbrella.ID).flash();
            }

            this.output = MathUtils.floor(((float) this.output) * (1.0f + ((float) sin) * sin_potency)); // apply sin and round down

            if (this.output != this.base) { // check if it should highlight n stuff
                this.isModified = true;
            }

            if (this.output < 0) { // make sure it isn't negative
                this.output = 0;
            }
        }
    }
}
