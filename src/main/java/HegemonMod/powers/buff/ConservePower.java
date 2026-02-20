package HegemonMod.powers.buff;

import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class ConservePower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + ConservePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ConservePower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (3 - amount2) + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    @Override
    public void atStartOfTurn() {
        this.amount2 = 0;
        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.type == SKILL) {
            amount2 ++;
            if (amount2 == 3) {
                amount2 = 0;
                addToBot(new ApplyPowerAction(owner, owner, new ArtifactPower(owner, amount)));
            }
            updateDescription();
        }
    }

    public AbstractPower makeCopy() {return new ConservePower(owner, amount);}
}
