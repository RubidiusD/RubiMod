package HegemonMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;

public class SkillBookAction extends AbstractGameAction {
    private final boolean upgraded;
    private final CardLibrary.LibraryType color;
    private final AbstractCard.CardRarity rarity;
    private final boolean single_use;

    public SkillBookAction(AbstractCard.CardRarity rarity, CardLibrary.LibraryType color_, boolean upgraded, boolean single_use) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
        this.color = color_;
        this.rarity = rarity;
        this.single_use = single_use;
    }

    public SkillBookAction(AbstractCard.CardRarity rarity, CardLibrary.LibraryType color_) {
        this(rarity, color_, false, false);
    }

    public void update() {
        ArrayList<AbstractCard> cardList = CardLibrary.getCardList(color);
        cardList.removeIf(card -> card.rarity != rarity || card.type != AbstractCard.CardType.SKILL);

        int choice = (int) (Math.random() * cardList.size());
        if (choice == cardList.size())
            choice = 0;

        AbstractCard tmp = cardList.get(choice);
        if (this.upgraded)
            tmp.upgrade();
        if (this.single_use)
        {
            tmp.exhaustOnUseOnce = true;
            tmp.exhaust = true;
        }

        addToTop(new MakeTempCardInHandAction(tmp));
        this.isDone = true;
    }
}
