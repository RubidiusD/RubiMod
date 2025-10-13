package HegemonMod.cards.skills.rare;

import HegemonMod.actions.ToxicityAction;
import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Cleanse extends BaseCard {
    public static final String ID = ("HegemonMod:" + Cleanse.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public Cleanse() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, magicNumber));
        addToBot(new ToxicityAction(p, -2));
    }

    @Override public AbstractCard makeCopy() { return new Cleanse(); }
}