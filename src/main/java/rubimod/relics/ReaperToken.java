package rubimod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import rubimod.character.Hegemon;



public class ReaperToken extends BaseRelic {
    private static final String NAME = ReaperToken.class.getSimpleName();
    public static final String ID = ("rubimod:" + NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.HEAVY;
    private boolean NewRelic = true;

    public ReaperToken()  { super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND); }

    @Override
    public void onEquip() {
        UnlockTracker.markRelicAsSeen(ID);
        AbstractDungeon.player.relics.remove(AbstractDungeon.player.getRelic(MysteriousStranger.ID));

        NewRelic = false;
    }

    @Override
    public void update() {
        super.update();
        if (!this.NewRelic && !AbstractDungeon.isScreenUp) {
            AbstractDungeon.combatRewardScreen.open();
            AbstractDungeon.combatRewardScreen.rewards.clear();
            if (AbstractDungeon.player.hasRelic(SuspiciousSalve.ID))
                AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(SuspiciousSalve.RARITY)));
            else
                AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(new SuspiciousSalve()));
            AbstractDungeon.combatRewardScreen.positionRewards();
//            AbstractDungeon.overlayMenu.proceedButton.setLabel(this.DESCRIPTIONS[1]);
            this.NewRelic = true;
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
        }
    }

    @Override
    public void atTurnStart() {
        this.flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, 1)));
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(MysteriousStranger.ID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
