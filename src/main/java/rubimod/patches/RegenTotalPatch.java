package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.powers.RegenPower;

@SpirePatch(
        clz= RegenPower.class,
        method= "updateDescription",
        paramtypez={}
)
public class RegenTotalPatch {
    public void Postfix(RegenPower power) {
        power.description = power.description + " For a total of #b" + (power.amount * (power.amount + 1)) / 2 + ".";
    }
}
