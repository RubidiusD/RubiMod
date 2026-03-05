package HegemonMod.cards.attacks.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.cards.attacks.Punition;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;

public class Flail extends BaseCard {
    public static final String ID = ("HegemonMod:" + Flail.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 5;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public Flail() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory

        cardsToPreview = new Punition();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i != magicNumber; i ++) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p, damage, NORMAL), SLASH_HORIZONTAL));
        }
        addToBot(new MakeTempCardInDrawPileAction(cardsToPreview, 1, true, true));
        addToBot(new DrawCardAction(1));
    }

    @Override public AbstractCard makeCopy() { return new Flail(); }
}