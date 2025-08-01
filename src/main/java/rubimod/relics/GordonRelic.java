package rubimod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Sin;

import java.util.ArrayList;



public class GordonRelic extends BaseRelic {
    private static final String NAME = GordonRelic.class.getSimpleName();
    public static final String ID = ("rubimod:" + NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public GordonRelic()  {
        super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip() {
        UnlockTracker.markRelicAsSeen(ID);
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead() || !m.hasPower(Sin.POWER_ID) || m.getPower(Sin.POWER_ID).amount == 0)
            return;
        ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
        monsters.remove(m);
        AbstractMonster target = monsters.get((int) (Math.random() * monsters.size()));
        this.addToTop(new ApplyPowerAction(target, AbstractDungeon.player, new Sin(target, m.getPower(Sin.POWER_ID).amount)));
        this.addToTop(new RelicAboveCreatureAction(target, this));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
