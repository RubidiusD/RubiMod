package HegemonMod.powers.buff;

import HegemonMod.powers.BasePower;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static java.lang.Math.min;

public class SurplusPower extends BasePower {
    public static final String POWER_ID = ("HegemonMod:" + SurplusPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SurplusPower(AbstractCreature owner, int amount) { super(POWER_ID, TYPE, TURN_BASED, owner, amount); }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void onSpecificTrigger() {
        int tempHP = TempHPField.tempHp.get(owner);
        System.out.println("amount of tempHP is" + tempHP);
        if (tempHP != 0) {
            addToTop(new HealAction(owner, owner, min(tempHP, amount)));
        }
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public AbstractPower makeCopy() { return new SurplusPower(owner, amount); }

    @SpirePatch2(clz= com.evacipated.cardcrawl.mod.stslib.patches.tempHp.BattleEnd.class, method= "Prefix", paramtypez = {AbstractRoom.class})
    public static class SpecificTrigger {
        @SpirePrefixPatch public static void Prefix() {
            if (player.hasPower(POWER_ID)) {
                player.getPower(POWER_ID).onSpecificTrigger();
            }
        }
    }
}
