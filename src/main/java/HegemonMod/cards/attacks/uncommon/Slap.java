package HegemonMod.cards.attacks.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;

public class Slap extends BaseCard {
    public static final String ID = ("HegemonMod:" + Slap.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 2;
    private static final int UPG_BLOCK = 1;
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 1;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Slap() {
        super(ID, info); // calls the parent constructor

        setBlock(BLOCK, UPG_BLOCK); // self-explanatory
        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, NORMAL), SLASH_HORIZONTAL));
        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new Slap(); }
}