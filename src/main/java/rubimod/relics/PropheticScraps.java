package rubimod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.DoomPower;
import rubimod.powers.debuff.Sin;



public class PropheticScraps extends BaseRelic {
    private static final String NAME = PropheticScraps.class.getSimpleName();
    public static final String ID = ("rubimod:" + NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.FLAT;

    private static final int DOOM = 1;

    public PropheticScraps()  {
        super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip() {
        UnlockTracker.markRelicAsSeen(ID);
    }

    @Override
    public void atBattleStartPreDraw() {
        this.flash();

        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            addToTop(new ApplyPowerAction(m, AbstractDungeon.player, new DoomPower(m, DOOM)));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DOOM + DESCRIPTIONS[1];
    }
}
