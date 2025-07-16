package rubimod.relics;

import com.megacrit.cardcrawl.unlock.UnlockTracker;
import rubimod.character.Hegemon;

import static rubimod.RubiMod.makeID;

public class PaperUmbrella extends BaseRelic {
    private static final String NAME = PaperUmbrella.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public PaperUmbrella()  {
        super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip() {
        super.onEquip();

        UnlockTracker.markRelicAsSeen(ID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
