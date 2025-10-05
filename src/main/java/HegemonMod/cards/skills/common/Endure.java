package HegemonMod.cards.skills.common;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.ToxicPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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

        setBlock(15, 5);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new ToxicPower(p, -1)));
    }

    @Override public AbstractCard makeCopy() { return new Endure(); }
}