package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.powers.PoisonPower;

@SpirePatch(
        clz= PoisonPower.class,
        method= "updateDescription",
        paramtypez={}
)
public class PoisonTotalPatch {
    public void Postfix(PoisonPower power) {
        power.description = power.description + " For a total of #b" + (power.amount * (power.amount + 1)) / 2 + ".";
    }
}
