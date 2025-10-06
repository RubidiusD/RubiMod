package HegemonMod.cards.powers.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.LikeBloodPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LikeBlood extends BaseCard {
    public static final String ID = ("HegemonMod:" + LikeBlood.class.getSimpleName());
    private static final BaseCard.CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = 4;

    public LikeBlood() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LikeBloodPower(p, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new LikeBlood(); }
}