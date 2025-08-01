package rubimod.powers.debuff;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import rubimod.powers.BasePower;
import rubimod.util.CustomTags;



public class LegacyofSin extends BasePower {
    public static final String POWER_ID = ("rubimod:" + LegacyofSin.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public LegacyofSin(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type, AbstractCard card)
    {
        if (card.tags.contains(CustomTags.EXECUTE))
            return damage * (1 + ((float) amount * 0.1f));
        return damage;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount * 10 + DESCRIPTIONS[1];
    }
}
