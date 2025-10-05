package HegemonMod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import HegemonMod.cards.BaseCard;
import HegemonMod.powers.debuff.Sin;
import HegemonMod.relics.ComicPage;

import static HegemonMod.util.CustomTags.NECROTIC;
import static HegemonMod.util.CustomTags.PUNISH;

public class Punish extends BaseCard {
    public static final String ID = ("HegemonMod:" + Punish.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 1;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Punish() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
        setSelfRetain(true);

        addTag(NECROTIC);
        addTag(PUNISH);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new Sin(m, magicNumber)));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override public AbstractCard makeCopy() { return new Punish(); }
}