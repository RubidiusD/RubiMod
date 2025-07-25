package rubimod.cards.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.actions.NecroticDamageAction;
import rubimod.cards.BaseCard;
import rubimod.powers.debuff.Sin;
import rubimod.relics.ComicPage;
import rubimod.util.CardStats;

public class Punish extends BaseCard {
    public static final String ID = makeID(Punish.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    private static final int SIN = 1;
    private static final int UPG_SIN = 1;

    public Punish() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
        setCustomVar("Sin", SIN, UPG_SIN);
        setExhaust(true);

        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(ComicPage.ID))
            upgradeMagicNumber(ComicPage.BUFF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new NecroticDamageAction(m, new DamageInfo(p, magicNumber), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(m, p, new Sin(m, customVar("Sin"))));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Punish();
    }
}
