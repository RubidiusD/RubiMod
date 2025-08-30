package rubimod.cards.attacks.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.HarvestAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.util.CustomTags;

public class Massage extends BaseCard {
    public static final String ID = ("rubimod:" + Massage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;

    public Massage() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setMagic(2, 1);
        setCustomVar("Stack", 2, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override public void update() {
                addToTop(new HealAction(m, p, magicNumber));
                addToTop(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
                for (AbstractCard c : p.hand.group) {
                    if (c instanceof Massage)
                        c.magicNumber += customVar("Stack");
                }
                for (AbstractCard c : p.drawPile.group) {
                    if (c instanceof Massage)
                        c.magicNumber += customVar("Stack");
                }
                for (AbstractCard c : p.discardPile.group) {
                    if (c instanceof Massage)
                        c.magicNumber += customVar("Stack");
                }

                this.isDone = true;
            }
        });
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Massage();
    }
}
