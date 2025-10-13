package HegemonMod.relics;

import HegemonMod.powers.debuff.Sin;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import HegemonMod.character.Hegemon;

public class PaperUmbrella extends BaseRelic {
    private static final String NAME = PaperUmbrella.class.getSimpleName();
    public static final String ID = ("HegemonMod:" + NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public static final float StrengthModifier = 0.03f;

    public PaperUmbrella()  { super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND); }

    @Override public void onEquip() {
        UnlockTracker.markRelicAsSeen(ID);
        Sin.Strength += StrengthModifier;
    }

    @Override
    public void onUnequip() {
        Sin.Strength -= StrengthModifier;
    }

    @Override public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
