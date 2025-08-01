package rubimod.cards.attacks.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Colin;
import rubimod.util.CardStats;

public class LeechHose extends BaseCard {
    public static final String ID = ("rubimod:" + LeechHose.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            -1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 5;
    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 1;

    public LeechHose() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < energyOnUse; i++) {
            addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        for (AbstractMonster enemy : AbstractDungeon.getMonsters().monsters) {
            addToBot(new ApplyPowerAction(enemy, p, new Colin(enemy, p, energyOnUse)));
            if (magicNumber > 0 && AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
                addToBot(new ApplyPowerAction(enemy, p, new StrengthPower(enemy, -energyOnUse)));
            }
        }
        addToBot(new LoseEnergyAction(energyOnUse));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new LeechHose();
    }
}
