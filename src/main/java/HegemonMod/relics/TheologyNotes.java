package HegemonMod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.debuff.Sin;

public class TheologyNotes extends BaseRelic {
    private static final String NAME = TheologyNotes.class.getSimpleName();
    public static final String ID = ("HegemonMod:" + NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.FLAT;

    private static final int SIN = 2;

    public TheologyNotes()  { super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND); }

    @Override public void onEquip() {
        UnlockTracker.markRelicAsSeen(ID);
    }

    @Override public void atBattleStartPreDraw() {
        this.flash();

        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            addToTop(new ApplyPowerAction(m, AbstractDungeon.player, new Sin(m, SIN)));
        }
    }

    @Override public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + SIN + DESCRIPTIONS[1];
    }
}
