package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.powers.RegenPower;

@SpirePatch2(
        clz= RegenPower.class,
        method= "updateDescription",
        paramtypez={}
)
public class RegenPatch {
    public void Postfix(RegenPower power) {
        power.description += " For a total of #b" + (power.amount * (power.amount + 1)) / 2 + ".";
    }
}
