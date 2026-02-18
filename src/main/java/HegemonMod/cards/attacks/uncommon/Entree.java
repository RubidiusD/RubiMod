package HegemonMod.cards.attacks.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.debuff.LeechToxin;
import HegemonMod.powers.debuff.Sin;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static HegemonMod.util.CustomTags.NECROTIC;
import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_LIGHT;
import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;

public class Entree extends BaseCard {
    public static final String ID = ("HegemonMod:" + Entree.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 3;
    private static final int MAGIC = 2;

    public Entree() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC); // self-explanatory
        setExhaust(true);
        setCostUpgrade(0);

        tags.add(NECROTIC);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, NORMAL), BLUNT_LIGHT));
        addToBot(new ApplyPowerAction(m, p, new LeechToxin(m, p, magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
        addToBot(new ApplyPowerAction(m, p, new Sin(m, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new Entree(); }
}