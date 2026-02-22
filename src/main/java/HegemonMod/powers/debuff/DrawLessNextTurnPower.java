package HegemonMod.powers.debuff;

import HegemonMod.powers.BasePower;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DrawLessNextTurnPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + DrawLessNextTurnPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public DrawLessNextTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.loadRegion("lessdraw");
    }

    @Override public void onInitialApplication() {
        --AbstractDungeon.player.gameHandSize;
    }

    @Override public void stackPower(int stackAmount) {
        if (AbstractDungeon.player.gameHandSize != 0) {
            super.stackPower(stackAmount);
            updateDescription();
            --AbstractDungeon.player.gameHandSize;
        }
        if (AbstractDungeon.player.gameHandSize == 0) {
            addToBot(new TalkAction(owner, CardCrawlGame.languagePack.getUIString("HegemonMod:0CardDraw").TEXT[0], 0.5f, 0.5f));
        }
    }

    @Override public void atStartOfTurnPostDraw() {
        this.flash();
        addToTop(new RemoveSpecificPowerAction(owner, owner, this));

        AbstractDungeon.player.gameHandSize += amount;
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() {return new DrawLessNextTurnPower(owner, amount);}
}