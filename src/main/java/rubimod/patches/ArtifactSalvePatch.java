package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.powers.ArtifactPower;

@SpirePatch(
        clz= ArtifactPower.class,
        method="onSpecificTrigger",
        paramtypez={}
)
public class ArtifactSalvePatch
{
    public static void Prefix(ArtifactPower power)
    {
        if (Math.random() < 0.5)
            power.amount += 1;
    }
}
