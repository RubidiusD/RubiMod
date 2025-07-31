package rubimod.cards.attacks.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.HarvestAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;
import rubimod.util.CustomTags;

public class BloodHarvest extends BaseCard {
    public static final String ID = makeID(BloodHarvest.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 1;

    public BloodHarvest() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory

        tags.add(CardTags.HEALING);
        tags.add(CustomTags.EXECUTE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HarvestAction(m, p, damage));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new BloodHarvest();
    }
}
