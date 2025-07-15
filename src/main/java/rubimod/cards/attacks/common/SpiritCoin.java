package rubimod.cards.attacks.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Bleeding;
import rubimod.util.CardStats;

public class SpiritCoin extends BaseCard {
    public static final String ID = makeID(SpiritCoin.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.SELF_AND_ENEMY,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 11;
    private static final int UPG_DAMAGE = 19;

    public SpiritCoin() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new ApplyPowerAction(p, p, new Bleeding(p, p)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new SpiritCoin();
    }
}
