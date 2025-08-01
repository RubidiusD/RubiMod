package rubimod.cards.skills.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Sin;
import rubimod.util.CardStats;

public class Feint extends BaseCard {
    public static final String ID = ("rubimod:" + Feint.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 3;
    private static final int MAGIC = 2;

    public Feint() {
        super(ID, info); // calls the parent constructor

        setBlock(BLOCK); // self-explanatory
        setMagic(MAGIC); // self-explanatory
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        if (m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND)
            addToBot(new ApplyPowerAction(m, p, new Sin(m, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Feint();
    }
}
