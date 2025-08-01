package rubimod.cards.attacks.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class HeroicPlay extends BaseCard {
    public static final String ID = ("rubimod:" + HeroicPlay.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.SELF_AND_ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 9;
    private static final int DAMAGE_UPG = 3;
    private static final int MAGIC = 1;

    public HeroicPlay() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, DAMAGE_UPG); // self-explanatory
        setMagic(MAGIC); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, magicNumber, false)));
        addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, magicNumber, false)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new HeroicPlay();
    }
}
