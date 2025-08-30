package rubimod.cards.skills.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import rubimod.actions.ApplyNecrotoxinAction;
import rubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.buff.RecoveryPower;
import rubimod.powers.debuff.Sin;

public class Recovery extends BaseCard {
    public static final String ID = ("rubimod:" + Recovery.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 3;
    private static final int ARTIFACT = 2;

    public Recovery() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setCustomVar("Artifact", ARTIFACT);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new RecoveryPower(m, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, customVar("Artifact"))));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Recovery();
    }
}
