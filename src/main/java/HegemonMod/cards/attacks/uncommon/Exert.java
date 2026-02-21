package HegemonMod.cards.attacks.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class Exert extends BaseCard {
    public static final String ID = ("HegemonMod:" + Exert.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 23;
    private static final int UPG_DAMAGE = 6;
    private static final int MAGIC = 2;

    public Exert() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setMagic(MAGIC); // self-explanatory

        shuffleBackIntoDrawPile = true;
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false)));
        for (int index = 0; index != magicNumber; index ++) {
            addToBot(new ApplyPowerAction(p, p, new DrawReductionPower(p, 1)));
        }
    }

    @Override public AbstractCard makeCopy() { return new Exert(); }
}