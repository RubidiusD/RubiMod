package HegemonMod.cards.attacks.common;

import HegemonMod.HegemonMod;
import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static HegemonMod.util.CustomTags.NECROTIC;

public class Crunch extends BaseCard {
    public static final String ID = ("HegemonMod:" + Crunch.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Crunch() {
        super(ID, info); // calls the parent constructor

        setDamage(0);
        setCostUpgrade(0);
        setMagic(3);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        baseDamage = HegemonMod.ToxicityThisTurn * magicNumber;
        calculateCardDamage(m);
        addToBot(new AbstractGameAction() {
            @Override public void update() {
                addToTop(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

                this.isDone = true;
            }
        });
    }

    @Override public void applyPowers() {
        this.baseDamage = HegemonMod.ToxicityThisTurn * 3;
        super.applyPowers();
    }

    @Override public AbstractCard makeCopy() { return new Crunch(); }
}