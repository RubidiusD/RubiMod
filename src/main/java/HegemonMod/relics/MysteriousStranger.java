package HegemonMod.relics;

import HegemonMod.powers.buff.MysteriousPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import HegemonMod.character.Hegemon;

public class MysteriousStranger extends BaseRelic {
    private static final String NAME = MysteriousStranger.class.getSimpleName();
    public static final String ID = ("HegemonMod:" + NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public MysteriousStranger()  { super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND); }

    @Override public void onEquip() {
        UnlockTracker.markRelicAsSeen(ID);
    }

    @Override public void atBattleStart() {
        this.flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, 1)));
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MysteriousPower(AbstractDungeon.player, 1)));
    }

    @Override public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
