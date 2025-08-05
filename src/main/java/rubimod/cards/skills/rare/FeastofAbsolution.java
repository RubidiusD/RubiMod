package rubimod.cards.skills.rare;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.AbsolutionAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class FeastofAbsolution extends BaseCard {
    public static final String ID = ("rubimod:" + FeastofAbsolution.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF_AND_ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;

    public FeastofAbsolution() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setCustomVar("Heals", 0, 1);
        setExhaust(true);

        addTag(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new GainEnergyAction(magicNumber));
        addToBot(new AbsolutionAction(m, p, (customVar("Heals") != 0)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FeastofAbsolution();
    }
}
