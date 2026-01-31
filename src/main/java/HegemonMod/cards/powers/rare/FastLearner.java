package HegemonMod.cards.powers.rare;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.LearnedPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class FastLearner extends BaseCard {
    public static final String ID = ("HegemonMod:" + FastLearner.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.NONE,
            -2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public FastLearner() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }

    @Override public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) { }

    @Override public void triggerWhenDrawn() {
        addToTop(new ApplyPowerAction(player, player, new LearnedPower(player, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new FastLearner(); }
}
