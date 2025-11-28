package HegemonMod.cards.powers.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.SurplusPower;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LifeSurplus extends BaseCard {
    public static final String ID = ("HegemonMod:" + LifeSurplus.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 4;
    private static final int TEMP_HP = 5;
    private static final int UPG_TEMP_HP = 1;

    public LifeSurplus() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
        setCustomVar("HP", TEMP_HP, UPG_TEMP_HP);

        addTag(CardTags.HEALING);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SurplusPower(p, magicNumber)));
        addToBot(new AddTemporaryHPAction(p, p, customVar("HP")));
    }

    @Override public AbstractCard makeCopy() { return new LifeSurplus(); }
}