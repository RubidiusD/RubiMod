package HegemonMod.patches;

import basemod.helpers.CardBorderGlowManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatches2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Sunder;
import com.megacrit.cardcrawl.cards.colorless.HandOfGreed;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.cards.purple.LessonLearned;
import com.megacrit.cardcrawl.cards.red.Feed;
import HegemonMod.util.CustomTags;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;

@SpirePatches2({
    @SpirePatch2(clz= Feed.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
    @SpirePatch2(clz= Sunder.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
    @SpirePatch2(clz= LessonLearned.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
    @SpirePatch2(clz= HandOfGreed.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
    @SpirePatch2(clz= RitualDagger.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
    @SpirePatch2(cls= "BuxomMod.cards.HungryDwarf", method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "BuxomMod"),
    @SpirePatch2(cls= "Cards.FoxHime.Attack.DispersionSex", method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "FoxHime"),
    @SpirePatch2(cls= "hermit.cards.DeadOrAlive", method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "Hermit"),
    @SpirePatch2(cls= "hermit.cards.DeadOrAlive", method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "downfall"),
    @SpirePatch2(cls= "slimebound.cards.MassFeed", method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "downfall"),
    @SpirePatch2(cls= "fakermod.cards.projection.Arrow", method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "FakerMod"),
    @SpirePatch2(cls= "dumbjokedivamod.cards.rare.FameAndFortune", method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "dumbjokedivamod"),
    @SpirePatch2(cls= "divapack.cards.attacks.Demonstrate", method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "DivaPack")
})
public class ExecutePatch {
    @SpirePostfixPatch public static void Postfix(AbstractCard __instance) {
        __instance.tags.add(CustomTags.EXECUTE);
    }

    public static CardBorderGlowManager.GlowInfo ExecuteGlow() {
        return new CardBorderGlowManager.GlowInfo() {
            @Override public Color getColor(AbstractCard card) { return Color.GOLD; }
            @Override public String glowID() { return ("HegemonMod:ExecuteGlow"); }
            @Override public boolean test(AbstractCard card) {
                return ((card.tags.contains(CustomTags.EXECUTE) && canExecute(card.damage))
                );
            }
        };
    }

    public static boolean canExecute(int damage) {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.halfDead && !m.isDead && m.currentHealth <= damage && !m.hasPower(MinionPower.POWER_ID)) {
                return true;
            }
        }
        return false;
    }
}
