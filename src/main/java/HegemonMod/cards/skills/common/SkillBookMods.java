package HegemonMod.cards.skills.common;

import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import HegemonMod.actions.SkillBookAction;
import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;

import java.util.ArrayList;

import static HegemonMod.util.CustomTags.SKILL_BOOK;

public class SkillBookMods extends BaseCard {
    public static final String ID = ("HegemonMod:" + SkillBookMods.class.getSimpleName());
    public static ArrayList<CardLibrary.LibraryType> Colours = new ArrayList<>();
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    public SkillBookMods() {
        super(ID, info); // calls the parent constructor

        setMagic(0, 1);
        setExhaust(true);
        addTag(SKILL_BOOK);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SkillBookAction(CardRarity.COMMON, Colours, (magicNumber == 1), false));
    }

    public static void setColours() {
        for (AbstractCard.CardColor colour : BaseMod.getCardColors()) {
            if (isAccepted(colour)) {
                Colours.add(CardLibrary.LibraryType.valueOf(colour.toString()));
            }
        }
        if (Colours.isEmpty()) {
            BaseMod.removeCard(SkillBookMods.ID, Hegemon.Meta.CARD_COLOR);
        }
    }

    private static final String[] rejectedColors = {"Hegemon", "Saber", "Projections", "Extra", "Other_COLOR", "Noble_Phantasm_COLOR"};

    public static boolean isAccepted(CardColor c) {
        for (String s : rejectedColors) {
            if (c.toString().equals(s)) {
                return false;
            }
        }
        return true;
    }

    @Override public AbstractCard makeCopy() { return new SkillBookMods(); }
}
