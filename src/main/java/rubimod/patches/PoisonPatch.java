package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.powers.PoisonPower;

@SpirePatch2(
        clz= PoisonPower.class,
        method= "updateDescription",
        paramtypez={}
)
public class PoisonPatch {
    public void Postfix(PoisonPower power) {
        power.description += " For a total of #b" + (power.amount * (power.amount + 1)) / 2 + ".";
    }
}
