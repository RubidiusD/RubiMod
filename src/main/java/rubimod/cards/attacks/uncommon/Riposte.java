package rubimod.cards.attacks.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.BaseCard;
import rubimod.cards.skills.Punish;
import rubimod.character.Hegemon;
import rubimod.util.CardStats;

public class Riposte extends BaseCard {
    public static final String ID = ("rubimod:" + Riposte.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 4;
    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 1;

    public Riposte() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory

        cardsToPreview = new Punish();
    }

    @Override
    public void upgrade() {
        super.upgrade();

        cardsToPreview.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (m.intent.equals(AbstractMonster.Intent.ATTACK) || m.intent.equals(AbstractMonster.Intent.ATTACK_BUFF) || m.intent.equals(AbstractMonster.Intent.ATTACK_DEBUFF) || m.intent.equals(AbstractMonster.Intent.ATTACK_DEFEND))
            for (int i = 0; i < 2; i++) {
                AbstractCard new_card = new Punish();
                if (magicNumber > 0) new_card.upgrade();
                addToBot(new MakeTempCardInHandAction(new_card));
            }
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Riposte();
    }
}
