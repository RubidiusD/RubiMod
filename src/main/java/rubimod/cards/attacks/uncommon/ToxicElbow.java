package rubimod.cards.attacks.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.ApplyNecrotoxinAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class ToxicElbow extends BaseCard {
    public static final String ID = makeID(ToxicElbow.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 4;
    private static final int MAGIC = 3;

    public ToxicElbow() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC); // self-explanatory
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyNecrotoxinAction(m, p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new ToxicElbow();
    }
}
