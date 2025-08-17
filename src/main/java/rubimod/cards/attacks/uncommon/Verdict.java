package rubimod.cards.attacks.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.LegacyofSin;
import rubimod.util.CardStats;

import static rubimod.powers.debuff.LegacyofSin.calculateExecute;

public class Verdict extends BaseCard {
    public static final String ID = ("rubimod:" + Verdict.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 0;
    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 3;

    public Verdict() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), (this.damage > 15) ? AbstractGameAction.AttackEffect.LIGHTNING : AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        float tmp = 0;
        if (mo != null)
        {
            tmp = 0.01f * magicNumber * (mo.maxHealth - mo.currentHealth);
            if (mo.hasPower(LegacyofSin.POWER_ID))
                tmp = calculateExecute(tmp, mo.getPower(LegacyofSin.POWER_ID).amount);
        }
        this.baseDamage = (int) (tmp + 0.5f);

        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION + "(" + this.damage + ")";
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Verdict();
    }
}
