package HegemonMod.cards.skills.uncommon;

import HegemonMod.actions.SkillBookAction;
import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static HegemonMod.util.CustomTags.SKILL_BOOK;

public class SkillBookColorless extends BaseCard {
    public static final String ID = ("HegemonMod:" + SkillBookColorless.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    public SkillBookColorless() {
        super(ID, info); // calls the parent constructor

        setMagic(0, 1);
        setExhaust(true);
        addTag(SKILL_BOOK);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SkillBookAction(CardRarity.UNCOMMON, CardLibrary.LibraryType.COLORLESS, (magicNumber == 1), false));
    }

    @Override public AbstractCard makeCopy() { return new SkillBookColorless(); }
}