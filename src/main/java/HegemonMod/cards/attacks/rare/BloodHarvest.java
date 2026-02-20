package HegemonMod.cards.attacks.rare;

import HegemonMod.HegemonMod;
import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisParticle;

import static HegemonMod.util.CustomTags.EXECUTE;
import static HegemonMod.util.CustomTags.NECROTIC;
import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;

public class BloodHarvest extends BaseCard {
    public static final String ID = ("HegemonMod:" + BloodHarvest.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 11;
    private static final int UPG_DAMAGE = 4;

    public BloodHarvest() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setExhaust(true);

        addTag(NECROTIC);
        addTag(EXECUTE);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() { @Override public void update() {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, AttackEffect.BLUNT_HEAVY));
            calculateCardDamage(m);
            m.damage(new DamageInfo(p, damage, NORMAL));
            if (HegemonMod.debuffCount != 0 && (m.isDying || m.currentHealth <= 0) && !m.halfDead && !m.hasPower("Minion")) {
                p.heal(HegemonMod.debuffCount);
                AbstractDungeon.effectList.add(new HemokinesisParticle(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY, true));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }});
    }

    @Override public AbstractCard makeCopy() { return new BloodHarvest(); }
}
