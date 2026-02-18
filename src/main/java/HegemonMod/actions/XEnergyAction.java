package HegemonMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class XEnergyAction extends AbstractGameAction {
    public interface FunctionR {
        void execute();
    }
    public interface FunctionV {
        void execute(int v);
    }

    FunctionR functionR;
    FunctionV functionV;
    int energyOnUse;
    boolean freeToPlayOnce;
    int modifier;

    public XEnergyAction(int energyOnUse, boolean freeToPlayOnce, FunctionR functionR, FunctionV functionV, int modifier) {
        this.functionR = functionR;
        this.functionV = functionV;
        this.energyOnUse = energyOnUse;
        this.freeToPlayOnce = freeToPlayOnce;
        this.modifier = modifier;
    }

    public XEnergyAction(int energyOnUse, boolean freeToPlayOnce, FunctionR functionR, FunctionV functionV) {
        this(energyOnUse, freeToPlayOnce, functionR, functionV, 0);
    }

    public XEnergyAction(int energyOnUse, boolean freeToPlayOnce, FunctionR function, int modifier) {
        this(energyOnUse, freeToPlayOnce, function, null, modifier);
    }

    public XEnergyAction(int energyOnUse, boolean freeToPlayOnce, FunctionV function, int modifier) {
        this(energyOnUse, freeToPlayOnce, null, function, modifier);
    }

    public XEnergyAction(int energyOnUse, boolean freeToPlayOnce, FunctionR function) {
        this(energyOnUse, freeToPlayOnce, function, null, 0);
    }

    public XEnergyAction(int energyOnUse, boolean freeToPlayOnce, FunctionV function) {
        this(energyOnUse, freeToPlayOnce, null, function, 0);
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (AbstractDungeon.player.hasRelic("Chemical X")) {
            effect += 2;
            AbstractDungeon.player.getRelic("Chemical X").flash();
        }

        if (functionR != null) {
            for (int i = 0; i != effect; i ++) {
                functionR.execute();
            }
        }
        if (functionV != null) {
            functionV.execute(effect);
        }

        if (!freeToPlayOnce && energyOnUse != -1) {
            addToTop(new LoseEnergyAction(energyOnUse));
        }

        this.isDone = true;
    }
}
