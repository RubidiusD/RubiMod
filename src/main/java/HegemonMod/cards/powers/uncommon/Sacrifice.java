package HegemonMod.cards.powers.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.debuff.LeechToxin;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.RitualPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Sacrifice extends BaseCard {
    public static final String ID = ("HegemonMod:" + Sacrifice.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 5;
    private static final int LEECH = 10;

    public Sacrifice() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setCustomVar("Ritual", 0, 1);
        setCustomVar("Leech", LEECH);
    }

    @Override public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return !p.hasPower(ArtifactPower.POWER_ID);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        if (customVar("Ritual") != 0)
            addToBot(new ApplyPowerAction(p, p, new RitualPower(p, 1, true)));
        addToBot(new ApplyPowerAction(p, p, new LeechToxin(p, p, customVar("Leech"))));
    }

    @Override public AbstractCard makeCopy() { // Optional
        return new Sacrifice();
    }
}
