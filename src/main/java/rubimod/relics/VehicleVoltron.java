package rubimod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import rubimod.character.Hegemon;

import static rubimod.RubiMod.makeID;

public class VehicleVoltron extends BaseRelic {
    private static final String NAME = VehicleVoltron.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.HEAVY;

    private static final int PLAYER_REGEN = 5;
    private static final int ENEMY_REGEN = 5;

    public VehicleVoltron()  {
        super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip() {
        super.onEquip();

        UnlockTracker.markRelicAsSeen(ID);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();

        this.flash();
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, PLAYER_REGEN)));

        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            addToTop(new ApplyPowerAction(m, AbstractDungeon.player, new RegenPower(m, ENEMY_REGEN)));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + PLAYER_REGEN + DESCRIPTIONS[1] + ENEMY_REGEN + DESCRIPTIONS[2];
    }
}
