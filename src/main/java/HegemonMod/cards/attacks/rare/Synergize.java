package HegemonMod.cards.attacks.rare;

import HegemonMod.actions.ToxicityAction;
import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.debuff.GentlePower;
import HegemonMod.powers.debuff.LeechToxin;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static HegemonMod.util.CustomTags.NECROTIC;

public class Synergize extends BaseCard {
    public static final String ID = ("HegemonMod:" + Synergize.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 12;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 3;
    private static final int TOXIC = 2;
    private static final int UPG_TOXIC = 2;
    private static final int LEECH = 3;
    private static final int UPG_LEECH = 1;

    public Synergize() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("Toxic", TOXIC, UPG_TOXIC);
        setCustomVar("Leech", LEECH, UPG_LEECH);

        addTag(NECROTIC);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new ApplyPowerAction(p, p, new GentlePower(p, magicNumber)));
        addToBot(new ToxicityAction(p, customVar("Toxic")));
        addToBot(new ApplyPowerAction(m, p, new LeechToxin(m, p, customVar("Leech"))));
    }

    @Override public AbstractCard makeCopy() { return new Synergize(); }
}
