package rubimod;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import rubimod.powers.Sin;

public class NecroticDamageInfo extends DamageInfo {
    public NecroticDamageInfo(AbstractCreature damageSource, int base) {
        super(damageSource, base, DamageType.THORNS);
    }

    public NecroticDamageInfo(AbstractCreature damageSource, int base, DamageType damageType) {
        super(damageSource, base, damageType);
    }

    @Override
    public void applyPowers(AbstractCreature owner, AbstractCreature target) {
        super.applyPowers(owner, target);

        int sin = target.getPower(Sin.POWER_ID).amount;
        if (sin != 0) {
            float tmp = (float)this.output;

            tmp *= (1.0f + sin * 0.1f);

            this.output = MathUtils.floor(tmp);

            if (this.base != this.output) {
                this.isModified = true;
            }

            if (this.output < 0) {
                this.output = 0;
            }
        }
    }
}
