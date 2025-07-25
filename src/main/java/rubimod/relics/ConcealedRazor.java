package rubimod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Bleeding;

import static rubimod.RubiMod.makeID;

public class ConcealedRazor extends BaseRelic {
    private static final String NAME = ConcealedRazor.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public ConcealedRazor()  {
        super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStartPreDraw() {
        addToTop(new ApplyPowerAction(AbstractDungeon.player,  AbstractDungeon.player, new Bleeding(AbstractDungeon.player, AbstractDungeon.player)));
    }

    public void onEquip() {
        UnlockTracker.markRelicAsSeen(ID);
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
