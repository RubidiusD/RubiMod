package rubimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.NecroticDamageInfo;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class LashOut extends BaseCard {
    public static final String ID = makeID(LashOut.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 2;
    private static final int MAGIC = 0;
    private static final int MAGIC_UPG = 3;

    public LashOut() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC, MAGIC_UPG); // self-explanatory

        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        if (magicNumber != baseMagicNumber)
        {
            addToBot(new DamageAction(m, new NecroticDamageInfo(p, magicNumber)));
        }
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new LashOut();
    }
}
