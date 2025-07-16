package rubimod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import rubimod.character.Hegemon;

import static rubimod.RubiMod.makeID;

public class SuspiciousSalve extends BaseRelic {
    private static final String NAME = SuspiciousSalve.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public SuspiciousSalve()  {
        super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip() {
        super.onEquip();

        UnlockTracker.markRelicAsSeen(ID);
    }

    @Override
    public void onTrigger() {
        super.onTrigger();

        System.out.println("Salve being Triggered");
        if (Math.random() < 0.5)
        {
            System.out.println("Adding Artifact");
            flash();
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, 1)));
        }
        else
            System.out.println("Letting Artifact be removed");
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
