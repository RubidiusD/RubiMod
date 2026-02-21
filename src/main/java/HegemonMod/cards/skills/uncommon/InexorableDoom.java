package HegemonMod.cards.skills.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.debuff.DoomPower;
import HegemonMod.powers.debuff.LeechToxin;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class InexorableDoom extends BaseCard {
    public static final String ID = ("HegemonMod:" + InexorableDoom.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    public InexorableDoom() {
        super(ID, info); // calls the parent constructor

        setMagic(0, 1);
        setExhaust(true);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        if (magicNumber != 0) {
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 1, false)));
            addToBot(new ApplyPowerAction(m, p, new LeechToxin(m, p, 1)));
        }
        addToBot(new ApplyPowerAction(m, p, new DoomPower(m, 1)));
    }

    @Override public AbstractCard makeCopy() { return new InexorableDoom(); }
}