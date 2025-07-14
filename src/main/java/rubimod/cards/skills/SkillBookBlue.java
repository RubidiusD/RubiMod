package rubimod.cards.skills;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Coolheaded;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class SkillBookBlue extends BaseCard {
    public static final String ID = makeID(SkillBookBlue.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    public SkillBookBlue() {
        super(ID, info); // calls the parent constructor

        setMagic(0, 1);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (magicNumber == baseMagicNumber) {
            addToBot(new MakeTempCardInHandAction(new Coolheaded()));
        }
        else {
            AbstractCard new_card = new Coolheaded();
            new_card.upgrade();
            addToBot(new MakeTempCardInHandAction(new_card));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SkillBookBlue();
    }
}
