package HegemonMod.cards.skills.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.debuff.LeechToxin;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Vaccinate extends BaseCard {
    public static final String ID = ("HegemonMod:" + Vaccinate.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Vaccinate() {
        super(ID, info); // calls the parent constructor

        setMagic(3);
        setSelfRetain(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i != magicNumber; i ++) {
            addToBot(new ApplyPowerAction(p, p, new LeechToxin(p, p, 1)));
        }
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Vaccinate();
    }
}
