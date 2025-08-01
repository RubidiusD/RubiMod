package rubimod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import rubimod.RubiMod;
import rubimod.character.Hegemon;
import rubimod.subscriptions.ArtifactLostSubscriber;



public class SuspiciousSalve extends BaseRelic implements ArtifactLostSubscriber {
    private static final String NAME = SuspiciousSalve.class.getSimpleName();
    public static final String ID = ("rubimod:" + NAME);
    public static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public SuspiciousSalve()  {
        super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip() {
        UnlockTracker.markRelicAsSeen(ID);
    }

    @Override
    public void receiveArtifactLost(AbstractCreature owner) {
        if (owner.isPlayer && Math.random() < 0.5)
        {
            System.out.println("Salve-ing Artifact");
            flash();
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, 1)));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
