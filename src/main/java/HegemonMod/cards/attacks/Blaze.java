package HegemonMod.cards.attacks;

import HegemonMod.cards.BaseCard;
import HegemonMod.powers.debuff.Sin;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.BodySlam;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static HegemonMod.util.CustomTags.NECROTIC;
import static HegemonMod.util.CustomTags.PUNISH;
import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.FIRE;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class Blaze extends BaseCard {
    public static final String ID = ("HegemonMod:" + Blaze.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 0;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 4;

    public Blaze() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);

        addTag(NECROTIC);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        baseDamage = TempHPField.tempHp.get(p);
        calculateCardDamage(m);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), FIRE));
        addToBot(new AddTemporaryHPAction(p, p, magicNumber));
    }

    @Override public AbstractCard makeCopy() { return new Blaze(); }
}