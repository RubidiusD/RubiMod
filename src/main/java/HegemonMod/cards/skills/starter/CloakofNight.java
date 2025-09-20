package HegemonMod.cards.skills.starter;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.powers.FrailPower;

public class CloakofNight extends BaseCard {
    public static final String ID = ("HegemonMod:" + CloakofNight.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 8;

    public CloakofNight() {
        super(ID, info); // calls the parent constructor

        setBlock(BLOCK); // self-explanatory
        setCostUpgrade(1);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1)));
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, 1, false)));
    }

    @Override public AbstractCard makeCopy() { //Optional
        return new CloakofNight();
    }
}
