package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import rubimod.powers.debuff.Sin;
import rubimod.relics.ComicPage;

public class PunitionAction extends AbstractGameAction {
    AbstractCreature owner;
    AbstractCreature target;

    public PunitionAction(AbstractCreature owner, AbstractCreature target) {
        super();
        this.owner = owner;
        this.target = target;
    }

    public void update()
    {
        if (!target.hasPower(Sin.POWER_ID) || target.getPower(Sin.POWER_ID).amount == 0)
        {
            this.isDone = true;
            return;
        }
//        addToTop(new DamageAction(target, new NecroticDamageInfo(owner, target.getPower(Sin.POWER_ID).amount), AttackEffect.POISON));
        addToTop(new NecroticDamageAction(target, new DamageInfo(owner,
                target.getPower(Sin.POWER_ID).amount + ((AbstractDungeon.player.hasRelic(ComicPage.ID)) ? ComicPage.BUFF : 0),
                DamageInfo.DamageType.THORNS), AttackEffect.POISON));

        this.isDone = true;
    }
}
