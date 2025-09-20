package HegemonMod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import HegemonMod.character.Hegemon;

import static HegemonMod.util.CustomTags.PUNISH;

public class ComicPage extends BaseRelic {
    private static final String NAME = ComicPage.class.getSimpleName();
    public static final String ID = ("HegemonMod:" + NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.FLAT;

    public static final int BUFF = 2;

    public ComicPage()  { super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND); }

    @Override public void onEquip() {
        UnlockTracker.markRelicAsSeen(ID);
    }

    @Override public float atDamageModify(float damage, AbstractCard c) {
        return c.hasTag(PUNISH) ? damage + BUFF : damage;
    }

    @Override public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BUFF + DESCRIPTIONS[1];
    }
}
