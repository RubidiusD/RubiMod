package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
//import rubimod.powers.buff.BlessingsBounty;
//import rubimod.powers.buff.SinEaterPower;
import rubimod.relics.SuspiciousSalve;

@SpirePatch(
        clz= ArtifactPower.class,
        method="onSpecificTrigger",
        paramtypez={}
)
public class ArtifactPatch
{
    public static void Prefix(ArtifactPower power)
    {
        if (power.owner.isPlayer && AbstractDungeon.player.hasRelic(SuspiciousSalve.ID))
        {
            AbstractDungeon.player.getRelic(SuspiciousSalve.ID).onTrigger();
        }

        // old
//        if (power.owner.hasPower(BlessingsBounty.POWER_ID))
//        {
//            power.owner.getPower(BlessingsBounty.POWER_ID).onSpecificTrigger();
//        }
//
//        if (power.owner.hasPower(SinEaterPower.POWER_ID))
//        {
//            power.owner.getPower(SinEaterPower.POWER_ID).onSpecificTrigger();
//        }
    }
}
