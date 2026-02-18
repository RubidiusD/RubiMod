package HegemonMod.cards.skills.common;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.debuff.GentlePower;
import HegemonMod.powers.debuff.LeechToxin;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DisarmingCharm extends BaseCard {
    public static final String ID = ("HegemonMod:" + DisarmingCharm.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    public DisarmingCharm() {
        super(ID, info); // calls the parent constructor

        setBlock(10, 3);
        setMagic(1);
        shuffleBackIntoDrawPile = true;
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(m, p, new GentlePower(p, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new DisarmingCharm(); }
}