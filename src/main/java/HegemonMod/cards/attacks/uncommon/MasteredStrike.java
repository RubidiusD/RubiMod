package HegemonMod.cards.attacks.uncommon;

import HegemonMod.cards.BaseCard;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HEAVY;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardTags.STRIKE;
import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;

public class MasteredStrike extends BaseCard {
    public static final String ID = ("HegemonMod:" + MasteredStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public MasteredStrike() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory

        addTag(STRIKE);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, NORMAL), SLASH_HEAVY));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 1)));
        addToBot(new DiscardPileToTopOfDeckAction(p));
        if (magicNumber != 1) {
            addToBot(new DiscardPileToTopOfDeckAction(p));
        }
    }

    @Override public AbstractCard makeCopy() { return new MasteredStrike(); }
}