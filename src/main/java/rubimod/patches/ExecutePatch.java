package rubimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatches2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.HandOfGreed;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.cards.purple.LessonLearned;
import com.megacrit.cardcrawl.cards.red.Feed;
import rubimod.util.CustomTags;

@SpirePatches2({
    @SpirePatch2(clz= LessonLearned.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
    @SpirePatch2(clz= RitualDagger.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
    @SpirePatch2(clz= Feed.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
    @SpirePatch2(clz= HandOfGreed.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
    @SpirePatch2(cls= "BuxomMod.cards.HungryDwarf", method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "BuxomMod"),
    @SpirePatch2(cls= "hermit.cards.DeadOrAlive", method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "Hermit"),
    @SpirePatch2(cls= "hermit.cards.DeadOrAlive", method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "downfall"),
    @SpirePatch2(cls= "slimebound.cards.MassFeed", method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "downfall")
})
public class ExecutePatch {
    @SpirePostfixPatch
    public static void Postfix(AbstractCard __instance) {
        __instance.tags.add(CustomTags.EXECUTE);
    }
}
