package HegemonMod.cards.powers.rare;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.EfficiencyPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class Efficiency extends BaseCard {
    public static final String ID = ("HegemonMod:" + Efficiency.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Efficiency() {
        super(ID, info); // calls the parent constructor

        setInnate(false, true);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EfficiencyPower(p, 1)));
    }

    @Override public void triggerWhenDrawn() {
        AbstractCard c = this;
        addToBot(new AbstractGameAction() {
            public void update() {
                if (player.hand.contains(c))
                    addToTop(new NewQueueCardAction(c, null));
                this.isDone = true;
            }
        });
    }

    @Override public AbstractCard makeCopy() { return new Efficiency(); }
}