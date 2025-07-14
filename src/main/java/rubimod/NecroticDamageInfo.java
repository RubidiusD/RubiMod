package rubimod;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import rubimod.powers.Sin;

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
            this.output = MathUtils.floor((float) this.output * (1.0f + (float) sin * 0.1f)); // apply sin and round down

            if (this.base != this.output) { // check if it should highlight n stuff
                this.isModified = true;
            }

            if (this.output < 0) { // make sure it isn't negative
                this.output = 0;
            }
        }
    }
}
