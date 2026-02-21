package HegemonMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class GuardiansPrice extends BaseCard {
    public static final String ID = ("HegemonMod:" + GuardiansPrice.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public GuardiansPrice() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setExhaust(true);
        setEthereal(true);
    }

    @Override
    public void triggerWhenDrawn() {
        addToBot(new DrawCardAction(1));
        addToBot(new ApplyPowerAction(player, player, new DexterityPower(player, -1)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override public AbstractCard makeCopy() { return new GuardiansPrice(); }
}