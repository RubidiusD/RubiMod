package rubimod.cards.skills.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.ApplyNecrotoxinAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Harbinger;
import rubimod.powers.debuff.LeechToxin;
import rubimod.util.CardStats;

public class DoomsHarbinger extends BaseCard {
    public static final String ID = ("rubimod:" + DoomsHarbinger.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 5;
    private static final int HARBINGER = 1;

    public DoomsHarbinger() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setCustomVar("Harbinger", HARBINGER);
        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new LeechToxin(m, p, magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new Harbinger(m, customVar("Harbinger"))));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DoomsHarbinger();
    }
}
