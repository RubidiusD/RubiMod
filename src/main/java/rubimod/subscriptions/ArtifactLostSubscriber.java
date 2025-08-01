package rubimod.subscriptions;

import com.megacrit.cardcrawl.core.AbstractCreature;

public interface ArtifactLostSubscriber {
    void receiveArtifactLost(AbstractCreature owner);
}
