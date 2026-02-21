package HegemonMod.cards.skills.common;

import HegemonMod.cards.BaseCard;
import HegemonMod.cards.attacks.Punish;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;

public class Allege extends BaseCard {
    public static final String ID = ("HegemonMod:" + Allege.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    public Allege() {
        super(ID, info); // calls the parent constructor

        setBlock(4);
        setMagic(2, 1);
        setCustomVar("Punish", 1, 1);
        cardsToPreview = new Punish();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new MakeTempCardInHandAction(cardsToPreview.makeCopy(), customVar("Punish")));
        addToBot(new ScryAction(magicNumber));
        addToBot(new DrawCardAction(1));
        addToBot(new ApplyPowerAction(p, p, new DrawReductionPower(p, 1)));
    }

    @Override public AbstractCard makeCopy() { return new Allege(); }
}