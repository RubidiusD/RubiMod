package rubimod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import rubimod.character.Hegemon;



public class MysteriousStranger extends BaseRelic {
    private static final String NAME = MysteriousStranger.class.getSimpleName();
    public static final String ID = ("rubimod:" + NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    private static final int ARTIFACT = 2;

    public MysteriousStranger()  {
        super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip() {
        UnlockTracker.markRelicAsSeen(ID);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, ARTIFACT)));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + ARTIFACT + DESCRIPTIONS[1];
    }
}
