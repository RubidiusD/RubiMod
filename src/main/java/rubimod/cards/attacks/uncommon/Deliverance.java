package rubimod.cards.attacks.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.NecroticDamageAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Sin;
import rubimod.util.CardStats;

public class Deliverance extends BaseCard {
    public static final String ID = makeID(Deliverance.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 5;
    private static final int MAGIC = 0;

    public Deliverance() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC); // self-explanatory
        setSelfRetain(true);
        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
//        addToBot(new DamageAction(m, new NecroticDamageInfo(p, magicNumber)));
        addToBot(new NecroticDamageAction(m, new DamageInfo(p, magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new Sin(m, magicNumber)));
    }

    @Override
    public void onRetained() {
        super.onRetained();
        magicNumber += 2;
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Deliverance();
    }
}
