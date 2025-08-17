package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.powers.debuff.LeechToxin;

public class DiseaseAction extends AbstractGameAction {
    private final int amount;

    public DiseaseAction(AbstractMonster m, AbstractPlayer p, int amount) {
        super();
        this.source = p;
        this.target = m;
        this.amount = amount;
    }

    @Override
    public void update() {
        addToTop(new HealAction(target, source, target.maxHealth - target.currentHealth));
        addToTop(new ApplyPowerAction(target, source, new LeechToxin(target, source, amount)));

        this.isDone = true;
    }
}
