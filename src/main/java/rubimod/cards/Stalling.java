package rubimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rubimod.powers.buff.StallingPower;
import rubimod.util.CardStats;

public class Stalling extends BaseCard {
    public static final String ID = ("rubimod:" + Stalling.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Stalling() {
        super(ID, info); // calls the parent constructor

        setCustomVar("Strength", 0);
        setEthereal(true);
        setExhaust(true);
    }

    public Stalling setStrengthLoss(int strengthLoss) {upgradeCustomVar("Strength", strengthLoss); return this;}

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(StrengthPower.POWER_ID))
            p.getPower(StrengthPower.POWER_ID).reducePower(customVar("Strength"));
        else
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -customVar("Strength"))));
        addToBot(new RemoveSpecificPowerAction(p, p, StallingPower.POWER_ID));
    }

    @Override
    public void triggerOnExhaust() {
        use(AbstractDungeon.player, null);
    }


}
