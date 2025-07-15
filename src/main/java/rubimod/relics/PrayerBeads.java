package rubimod.relics;

import rubimod.character.Hegemon;

import static rubimod.RubiMod.makeID;

public class PrayerBeads extends BaseRelic {
    private static final String NAME = PrayerBeads.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public PrayerBeads()  {
        super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
