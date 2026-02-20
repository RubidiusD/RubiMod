package HegemonMod.powers.buff;

import HegemonMod.cards.GuardiansPrice;
import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.POWER;

public class GuardiansBlessingPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + GuardiansBlessingPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public GuardiansBlessingPower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.type == POWER) {
            addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount)));
            addToBot(new MakeTempCardInDrawPileAction(new GuardiansPrice(), amount, true, true));
        }
    }

    public AbstractPower makeCopy() {return new GuardiansBlessingPower(owner, amount);}
}
