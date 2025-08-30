package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class XEnergyAction extends AbstractGameAction {
    public interface Function {
            void execute();
    }

    Function function;
    int energyOnUse;
    boolean freeToPlayOnce;
    int modifier;

    public XEnergyAction(int energyOnUse, boolean freeToPlayOnce, Function function, int modifier) {
        this.function = function;
        this.energyOnUse = energyOnUse;
        this.freeToPlayOnce = freeToPlayOnce;
        this.modifier = modifier;
    }

    public XEnergyAction(int energyOnUse, boolean freeToPlayOnce, Function function) {
        this(energyOnUse, freeToPlayOnce, function, 0);
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

        for (int i = 0; i != effect; i ++) {
            function.execute();
        }

        if (!freeToPlayOnce) {
            addToTop(new LoseEnergyAction(energyOnUse));
        }

        this.isDone = true;
    }
}
