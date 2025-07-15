package rubimod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import rubimod.character.Hegemon;

import static rubimod.RubiMod.makeID;

public class ReaperToken extends BaseRelic {
    private static final String NAME = ReaperToken.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.HEAVY;

    public ReaperToken()  { super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND); }

    @Override
    public void atTurnStart() {
        super.atTurnStart();

        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, 1)));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
