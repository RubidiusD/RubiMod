package HegemonMod.cards.powers.rare;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import HegemonMod.powers.debuff.GiantSlayerPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class GiantSlayer extends BaseCard {
    public static final String ID = ("HegemonMod:" + GiantSlayer.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    public GiantSlayer() {
        super(ID, info); // calls the parent constructor

        setCostUpgrade(0);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (mo.hasPower(ArtifactPower.POWER_ID)) {
                return false;
            }
        }
        return true;
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AllEnemyApplyPowerAction(p, 1, (AbstractMonster mo) -> new GiantSlayerPower(mo, 1)));
    }

    @Override public AbstractCard makeCopy() { return new GiantSlayer(); }
}