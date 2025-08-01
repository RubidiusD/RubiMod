package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import rubimod.subscriptions.ArtifactLostSubscriber;

@SpirePatch2(
        clz= ArtifactPower.class,
        method="onSpecificTrigger",
        paramtypez={}
)
public class ArtifactPatch
{
    @SpirePrefixPatch
    public static void Prefix(ArtifactPower __instance)
    {
        for (AbstractRelic r : AbstractDungeon.player.relics)
            if (r instanceof ArtifactLostSubscriber)
                ((ArtifactLostSubscriber) r).receiveArtifactLost(__instance.owner);
        for (AbstractPower p : AbstractDungeon.player.powers)
            if (p instanceof ArtifactLostSubscriber)
                ((ArtifactLostSubscriber) p).receiveArtifactLost(__instance.owner);
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
            for (AbstractPower p : m.powers)
                if (p instanceof ArtifactLostSubscriber)
                    ((ArtifactLostSubscriber) p).receiveArtifactLost(__instance.owner);
    }
}
