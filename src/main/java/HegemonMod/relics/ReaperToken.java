package HegemonMod.relics;

import HegemonMod.powers.buff.MysteriousPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import HegemonMod.character.Hegemon;

public class ReaperToken extends BaseRelic {
    private static final String NAME = ReaperToken.class.getSimpleName();
    public static final String ID = ("HegemonMod:" + NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.HEAVY;

    public ReaperToken()  { super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND); }

    @Override public void onEquip() {
        UnlockTracker.markRelicAsSeen(ID);
        AbstractDungeon.player.relics.remove(AbstractDungeon.player.getRelic(MysteriousStranger.ID));
    }

    @Override public void atBattleStart() {
        this.flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, 2)));
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MysteriousPower(AbstractDungeon.player, 2)));
    }

    @Override public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(MysteriousStranger.ID);
    }

    @Override public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
