package rubimod.cards.skills.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.FeastAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class FeastofBlood extends BaseCard {
    public static final String ID = ("rubimod:" + FeastofBlood.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    private static final int REGEN = 1;
    private static final int UPG_REGEN = 1;

    public FeastofBlood() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("Regen", REGEN, UPG_REGEN);

        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new FeastAction(m, p, magicNumber, customVar("Regen")));
    }
}
