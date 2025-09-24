package HegemonMod.cards.skills.common;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.RecoveryPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class Recover extends BaseCard {
    public static final String ID = ("HegemonMod:" + Recover.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF_AND_ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Recover() {
        super(ID, info); // calls the parent constructor

        setMagic(3);
        setExhaust(true, false);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 2)));
        addToBot(new ApplyPowerAction(m, p, new RecoveryPower(m, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { //Optional
        return new Recover();
    }
}
