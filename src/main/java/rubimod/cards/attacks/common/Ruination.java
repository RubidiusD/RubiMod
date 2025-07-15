package rubimod.cards.attacks.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.NecroticDamageAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class Ruination extends BaseCard {
    public static final String ID = makeID(Ruination.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 4;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 3;

    public Ruination() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
//        addToBot(new DamageAction(m, new NecroticDamageInfo(p, magicNumber)));
        addToBot(new NecroticDamageAction(m, new DamageInfo(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Ruination();
    }
}
