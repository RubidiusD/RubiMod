package rubimod.cards.skills.common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.SkillBookAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class SkillBookGreen extends BaseCard {
    public static final String ID = ("rubimod:" + SkillBookGreen.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    public SkillBookGreen() {
        super(ID, info); // calls the parent constructor

        setMagic(0, 1);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SkillBookAction(CardRarity.COMMON, CardLibrary.LibraryType.GREEN, (magicNumber == 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SkillBookGreen();
    }
}
