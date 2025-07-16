package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import rubimod.powers.buff.LearnedPower;

import java.util.ArrayList;

public class SkillBookAction extends AbstractGameAction {
    private final boolean upgraded;
    private final CardLibrary.LibraryType color;
    private final AbstractCard.CardRarity rarity;
    private SingleUse single_use;

    public enum SingleUse {
        TRUE,
        FALSE
    }

    public SkillBookAction(AbstractCard.CardRarity rarity, CardLibrary.LibraryType color_, boolean upgraded, SingleUse single_use) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
        this.color = color_;
        this.rarity = rarity;
        this.single_use = single_use;
    }

    public SkillBookAction(AbstractCard.CardRarity rarity, CardLibrary.LibraryType color_) {
        this(rarity, color_, false, SingleUse.FALSE);
    }

    public SkillBookAction(AbstractCard.CardRarity rarity, CardLibrary.LibraryType color_, boolean upgraded) {
        this(rarity, color_,  upgraded, SingleUse.FALSE);
    }

    public SkillBookAction(AbstractCard.CardRarity rarity, CardLibrary.LibraryType color_, SingleUse single_use) {
        this(rarity, color_, false, single_use);
    }

    public void update() {
        ArrayList<AbstractCard> cardList = CardLibrary.getCardList(color);
        cardList.removeIf(card -> card.rarity != rarity || card.type != AbstractCard.CardType.SKILL);

        int choice = (int) (Math.random() * cardList.size());
        if (choice == cardList.size())
            choice = 0;

        AbstractCard tmp = cardList.get(choice);
        if (upgraded)
            tmp.upgrade();
        if (tmp.cost > 0 && AbstractDungeon.player.hasPower(LearnedPower.POWER_ID))
        {
            tmp.modifyCostForCombat(tmp.cost - 1);
        }
        if (single_use.equals(SingleUse.TRUE))
            tmp.exhaustOnUseOnce = true;

        addToTop(new MakeTempCardInHandAction(tmp));
        this.isDone = true;
    }
}
