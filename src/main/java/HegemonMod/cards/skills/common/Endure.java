package HegemonMod.cards.skills.common;

import HegemonMod.actions.ToxicityAction;
import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Endure extends BaseCard {
    public static final String ID = ("HegemonMod:" + Endure.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Endure() {
        super(ID, info); // calls the parent constructor

        setBlock(16, 5);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ToxicityAction(p, -1));
    }

    @Override public AbstractCard makeCopy() { return new Endure(); }
}