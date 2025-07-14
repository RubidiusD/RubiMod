package rubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;

public class SkillBookAction extends AbstractGameAction {
    private boolean upgraded;
    private CardLibrary.LibraryType color;
    private AbstractCard.CardRarity rarity;
    private AbstractPlayer owner;

    public SkillBookAction(AbstractPlayer player, AbstractCard.CardRarity rarity_, CardLibrary.LibraryType color_, boolean upgraded) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
        this.color = color_;
        this.owner = player;
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

        addToTop(new MakeTempCardInHandAction(tmp));
        this.isDone = true;
    }
}
