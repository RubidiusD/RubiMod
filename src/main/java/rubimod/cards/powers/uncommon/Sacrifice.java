package rubimod.cards.powers.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.RitualPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Bleeding;
import rubimod.powers.debuff.LeechToxin;
import rubimod.util.CardStats;

public class Sacrifice extends BaseCard {
    public static final String ID = makeID(Sacrifice.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 5;

    public Sacrifice() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setCustomVar("Ritual", 0, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        if (customVar("Ritual") == 1)
            addToBot(new ApplyPowerAction(p, p, new RitualPower(p, 1, true)));
        int artifact = p.getPower(ArtifactPower.POWER_ID).amount;
        addToBot(new RemoveSpecificPowerAction(p, p, ArtifactPower.POWER_ID));
        addToBot(new ApplyPowerAction(p, p, new Bleeding(p, p)));
        addToBot(new ApplyPowerAction(p, p, new LeechToxin(p, p, 99)));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, artifact)));

    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Sacrifice();
    }
}
