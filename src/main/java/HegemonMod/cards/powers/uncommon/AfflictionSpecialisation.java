package HegemonMod.cards.powers.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class AfflictionSpecialisation extends BaseCard {
    public static final String ID = ("HegemonMod:" + AfflictionSpecialisation.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 3;

    public AfflictionSpecialisation() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setInnate(true);
        setCostUpgrade(0);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new AfflictionSpecialisation(); }
}