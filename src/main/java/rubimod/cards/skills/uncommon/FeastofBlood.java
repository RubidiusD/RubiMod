package rubimod.cards.skills.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.BloodFeastAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class FeastofBlood extends BaseCard {
    public static final String ID = makeID(FeastofBlood.class.getSimpleName()); // makeID adds the mod name
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
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new BloodFeastAction(m, p, magicNumber, customVar("Regen")));
    }
}
