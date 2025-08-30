package rubimod.cards.attacks.rare;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import rubimod.actions.XEnergyAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.LeechToxin;

public class LeechHose extends BaseCard {
    public static final String ID = ("rubimod:" + LeechHose.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            -1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public LeechHose() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new XEnergyAction(energyOnUse, freeToPlayOnce, () -> {
            addToTop(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            addToTop(new AllEnemyApplyPowerAction(p, 1, (AbstractMonster mo) -> new LeechToxin(mo, p, magicNumber)));
            addToTop(new AllEnemyApplyPowerAction(p, 1, (AbstractMonster mo) -> new VulnerablePower(mo, 1, false)));
        }));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new LeechHose();
    }
}
