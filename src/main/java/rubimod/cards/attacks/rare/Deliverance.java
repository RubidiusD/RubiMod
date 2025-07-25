package rubimod.cards.attacks.rare;

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
            CardRarity.RARE,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 5;
    private static final int MAGIC = 0;
    private static final int INCREASE = 2;
    private static final int UPG_INCREASE = 1;

    public Deliverance() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC); // self-explanatory
        setSelfRetain(true);
        setCustomVar("Increase", INCREASE, UPG_INCREASE);
        setCustomVar("Sin", 0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new NecroticDamageAction(m, new DamageInfo(p, magicNumber, DamageInfo.DamageType.THORNS)));
        addToBot(new ApplyPowerAction(m, p, new Sin(m, customVar("Sin"))));
    }

    @Override
    public void onRetained() {
        this.upgradeMagicNumber(customVar("Increase"));
        this.upgradeCustomVar("Sin", customVar("Increase") - 1);
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Deliverance();
    }
}
