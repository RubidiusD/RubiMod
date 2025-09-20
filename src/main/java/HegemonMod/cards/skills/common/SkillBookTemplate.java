package HegemonMod.cards.skills.common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import HegemonMod.actions.SkillBookAction;
import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class SkillBookTemplate extends BaseCard {
    public static final String ID = ("HegemonMod:" + SkillBookTemplate.class.getSimpleName());
    public CardLibrary.LibraryType colour = Hegemon.Meta.LIBRARY_COLOR;
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    public SkillBookTemplate() {
        super(ID, info); // calls the parent constructor

        setMagic(0, 1);
        setExhaust(true);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SkillBookAction(CardRarity.COMMON, colour, (magicNumber == 1), false));
    }

    public AbstractCard setColour(CardLibrary.LibraryType colour) {
        this.colour = colour;
        String colour_name = colour.toString();
        cardID += colour_name;
        java.util.Map<String, String> dict = languagePack.getUIString("HegemonMod:SkillBooks").TEXT_DICT;
        if (dict.containsKey(colour_name)) {colour_name = dict.get(colour_name);}
        name = colour_name + name;
        rawDescription = rawDescription.substring(0, rawDescription.indexOf("?")) + colour_name + rawDescription.substring(rawDescription.indexOf("?") + 1);
        initializeDescription();
        return this;
    }

    @Override public AbstractCard makeCopy() { //Optional
        return new SkillBookTemplate().setColour(this.colour);
    }

    private static final String[] rejectedColors = {"Saber", "Projections", "Extra", "Other_COLOR", "Noble_Phantasm_COLOR"};
    public static boolean isAccepted(CardColor c) {
        for (String s : rejectedColors) {
            if (c.toString().equals(s)) {
                return false;
            }
        }
        return true;
    }
}
