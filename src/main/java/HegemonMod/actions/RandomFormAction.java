package HegemonMod.actions;

import HegemonMod.powers.buff.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static com.badlogic.gdx.math.MathUtils.random;

public class RandomFormAction extends AbstractGameAction {
    public RandomFormAction(AbstractCreature target) {
        this.target = target;
    }

    @Override public void update() {
        switch (random.nextInt(5)) {
            case (0):
                addToTop(new ApplyPowerAction(target, target, new SnakePower(target, 1)));
                break;
            case (1):
                addToTop(new ApplyPowerAction(target, target, new HierophantPower(target, 1)));
                break;
            case (2):
                addToTop(new ApplyPowerAction(target, target, new PlagueBearerPower(target, 1)));
                break;
            case (3):
                addToTop(new ApplyPowerAction(target, target, new HunterPower(target, 2)));
                break;
            case (4):
                addToTop(new ApplyPowerAction(target, target, new HermitPower(target, 6)));
                break;
        }

        this.isDone = true;
    }
}
