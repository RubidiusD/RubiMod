package rubimod.cards.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.NecroticDamageAction;
import rubimod.cards.BaseCard;
import rubimod.powers.debuff.Sin;
import rubimod.util.CardStats;

public class Punish2 extends BaseCard {
    public static final String ID = makeID(Punish2.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Punish2() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new NecroticDamageAction(m, new DamageInfo(p, magicNumber), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(m, p, new Sin(m, magicNumber - 1)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Punish2();
    }
}
