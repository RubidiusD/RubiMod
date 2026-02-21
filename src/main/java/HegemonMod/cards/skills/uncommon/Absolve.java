package HegemonMod.cards.skills.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.cards.attacks.Punish;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class Absolve extends BaseCard {
    public static final String ID = ("HegemonMod:" + Absolve.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Absolve() {
        super(ID, info); // calls the parent constructor

        cardsToPreview = new Punish();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        shuffleBackIntoDrawPile = true;
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() { @Override public void update() {
            int theSize = player.hand.size();
            addToTop(new MakeTempCardInHandAction(cardsToPreview.makeCopy(), theSize));
            addToTop(new DiscardAction(player, player, theSize, false));
            this.isDone = true;
        }});
    }

    @Override public AbstractCard makeCopy() { return new Absolve(); }
}