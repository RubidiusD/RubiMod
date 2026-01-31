package HegemonMod.cards.attacks.rare;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.ToxicPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static HegemonMod.util.CustomTags.NECROTIC;
import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.LIGHTNING;
import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;

public class Pronouncement extends BaseCard {
    public static final String ID = ("HegemonMod:" + Pronouncement.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            3 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 28;
    private static final int UPG_DAMAGE = 7;

    public Pronouncement() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory

        addTag(NECROTIC);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, damage, NORMAL, LIGHTNING));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -2)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -2)));
        addToBot(new ApplyPowerAction(p, p, new ToxicPower(p, -2)));
    }

    @Override public AbstractCard makeCopy() { return new Pronouncement(); }
}
