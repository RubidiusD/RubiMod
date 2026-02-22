package HegemonMod.cards.skills.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import HegemonMod.powers.debuff.DrawLessNextTurnPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class Loan extends BaseCard {
    public static final String ID = ("HegemonMod:" + Loan.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Loan() {
        super(ID, info); // calls the parent constructor

        setExhaust(true, false);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() { @Override public void update() {
            int effect = EnergyPanel.totalCount;
            if (energyOnUse != -1) {
                effect = energyOnUse;
            }
            System.out.println("totalCount" + EnergyPanel.totalCount);
            System.out.println("energyOnUse" + energyOnUse);
            System.out.println("effect" + effect);

            if (effect > 0) {
                addToTop(new DrawCardAction(effect));
                for (int index = 0; index != effect; index ++) {
                    addToTop(new ApplyPowerAction(p, p, new DrawLessNextTurnPower(p, 1)));
                }
            }

            this.isDone = true;
        }});
    }

    @Override public AbstractCard makeCopy() { return new Loan(); }
}