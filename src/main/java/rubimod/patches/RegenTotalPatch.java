package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.powers.RegenPower;

@SpirePatch(
        clz= RegenPower.class,
        method= "updateDescription",
        paramtypez={}
)
public class RegenTotalPatch {
    public static void Postfix(RegenPower power) {
        if (power.owner.isPlayer) {
            power.description = power.description + " For a total of #b" + (power.amount * (power.amount + 1)) / 2 + ".";
        }
    }
}
