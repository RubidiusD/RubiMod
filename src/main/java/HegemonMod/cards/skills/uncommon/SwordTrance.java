package HegemonMod.cards.skills.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConfusionPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class SwordTrance extends BaseCard {
    public static final String ID = ("HegemonMod:" + SwordTrance.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 3;

    public SwordTrance() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setCostUpgrade(0);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));
        addToBot(new ApplyPowerAction(p, p, new ConfusionPower(p)));
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

    @Override public AbstractCard makeCopy() { return new SwordTrance(); }
}