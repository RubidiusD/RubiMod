package HegemonMod.cards.skills.common;

import HegemonMod.actions.ToxicityAction;
import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IncreaseToxicity extends BaseCard {
    public static final String ID = ("HegemonMod:" + IncreaseToxicity.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public IncreaseToxicity() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
        setExhaust(true, false);
    }

    @Override public boolean canUpgrade() {
        return true;
    }

    @Override protected void upgradeName() {
        this.name = cardStrings.NAME + "+" + (this.timesUpgraded + 1);
        this.initializeTitle();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ToxicityAction(p, 2));
        addToBot(new DrawCardAction(magicNumber));
    }

    @Override public AbstractCard makeCopy() { return new IncreaseToxicity(); }
}