package HegemonMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;

import static HegemonMod.util.CustomTags.SKILL_BOOK;
import static com.badlogic.gdx.math.MathUtils.random;

public class SkillBookAction extends AbstractGameAction {
    private final boolean upgraded;
    private final ArrayList<CardLibrary.LibraryType> colours = new ArrayList<>();
    private final AbstractCard.CardRarity rarity;
    private final boolean single_use;

    public SkillBookAction(AbstractCard.CardRarity rarity, CardLibrary.LibraryType colour, boolean upgraded, boolean single_use) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
        this.colours.add(colour);
        this.rarity = rarity;
        this.single_use = single_use;
    }

    public SkillBookAction(AbstractCard.CardRarity rarity, ArrayList<CardLibrary.LibraryType> colours, boolean upgraded, boolean single_use) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
        this.colours.addAll(colours);
        this.rarity = rarity;
        this.single_use = single_use;
    }

    public SkillBookAction(AbstractCard.CardRarity rarity, CardLibrary.LibraryType color_) {
        this(rarity, color_, false, false);
    }

    public void update() {
        ArrayList<AbstractCard> cardList = new ArrayList<>();
        for (CardLibrary.LibraryType colour : colours) {
            cardList.addAll(CardLibrary.getCardList(colour));
        }
        cardList.removeIf(card -> card.rarity != rarity || card.type != AbstractCard.CardType.SKILL || card.hasTag(SKILL_BOOK));

        AbstractCard tmp = cardList.get(random.nextInt(cardList.size()));
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
