package rubimod.cards.skills.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.DiseaseAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class Disease extends BaseCard {
    public static final String ID = ("rubimod:" + Disease.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 3;

    public Disease() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiseaseAction(m, p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Disease();
    }
}
