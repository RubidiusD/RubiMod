package HegemonMod.cards.attacks.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;

public class Slap5 extends BaseCard {
    public static final String ID = ("HegemonMod:" + Slap5.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            3 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 6;
    private static final int MAGIC = 5;

    public Slap5() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC); // self-explanatory
        setCostUpgrade(2);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        for (int index = 0; index != magicNumber; index ++) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p, damage, NORMAL), SLASH_DIAGONAL));
        }
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -1)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -1)));
    }

    @Override public AbstractCard makeCopy() { return new Slap5(); }
}