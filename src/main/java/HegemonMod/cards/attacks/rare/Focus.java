package HegemonMod.cards.attacks.rare;

import HegemonMod.cards.BaseCard;
import HegemonMod.cards.attacks.Punition;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.HandOfGreed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static HegemonMod.util.CustomTags.NECROTIC;

public class Focus extends BaseCard {
    public static final String ID = ("HegemonMod:" + Focus.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 5;

    public Focus() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setExhaust(true);
        cardsToPreview = new Punition();

        addTag(NECROTIC);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new AbstractGameAction() { @Override public void update() {
            int count = 0;
            for (AbstractCard card : p.discardPile.group) {
                addToTop(new ExhaustSpecificCardAction(card, p.discardPile, true));
                count ++;
            }
            System.out.println("Number of cards exhausted: " + count);
            if (count != 0) {
                addToBot(new MakeTempCardInDiscardAction(new Punition(), count));
                System.out.println("Number of punitions being made: " + count);
            }

            isDone = true;
        }});
    }

    @Override public AbstractCard makeCopy() { return new Focus(); }
}
