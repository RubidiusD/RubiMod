package HegemonMod.powers.buff;

import HegemonMod.actions.ToxicityAction;
import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class ReservePower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + ReservePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ReservePower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (2 - amount2) + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    @Override
    public void atStartOfTurn() {
        this.amount2 = 0;
        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.type == ATTACK) {
            amount2 ++;
            if (amount2 == 2) {
                amount2 = 0;
                addToBot(new ToxicityAction(owner, amount));
            }
            updateDescription();
        }
    }

    public AbstractPower makeCopy() {return new ReservePower(owner, amount);}
}
