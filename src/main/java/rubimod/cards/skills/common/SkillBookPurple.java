package rubimod.cards.skills.common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.SkillBookAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class SkillBookPurple extends BaseCard {
    public static final String ID = makeID(SkillBookPurple.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    public SkillBookPurple() {
        super(ID, info); // calls the parent constructor

        setMagic(0, 1);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SkillBookAction(CardRarity.COMMON, CardLibrary.LibraryType.PURPLE, (magicNumber == 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SkillBookPurple();
    }
}
