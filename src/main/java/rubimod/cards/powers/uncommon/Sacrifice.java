package rubimod.cards.powers.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RitualPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Bleeding;
import rubimod.powers.debuff.LeechToxin;
import rubimod.util.CardStats;

public class Sacrifice extends BaseCard {
    public static final String ID = ("rubimod:" + Sacrifice.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 5;
    private static final int LEECH = 10;

    public Sacrifice() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setCustomVar("Ritual", 0, 1);
        setCustomVar("Leech", LEECH);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        if (customVar("Ritual") != 0)
            addToBot(new ApplyPowerAction(p, p, new RitualPower(p, 1, true)));

        if (!p.hasPower(Bleeding.POWER_ID))
        {
            p.powers.add(new Bleeding(p, p));
            p.powers.add(new LeechToxin(p, p, customVar("Leech")));
        }
        else if (p.hasPower(LeechToxin.POWER_ID))
            p.getPower(LeechToxin.POWER_ID).stackPower(customVar("Leech"));
        else
            p.powers.add(new LeechToxin(p, p, customVar("Leech")));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Sacrifice();
    }
}
