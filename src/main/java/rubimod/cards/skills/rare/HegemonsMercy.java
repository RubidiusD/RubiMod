package rubimod.cards.skills.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.MercyAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class HegemonsMercy extends BaseCard {
    public static final String ID = ("rubimod:" + HegemonsMercy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF_AND_ENEMY,
            3 // card cost!! (-1 is X, -2 is unplayable)
    );

    public HegemonsMercy() {
        super(ID, info); // calls the parent constructor

        setExhaust(true); // self-explanatory
        setCostUpgrade(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MercyAction(m, p));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new HegemonsMercy();
    }
}
