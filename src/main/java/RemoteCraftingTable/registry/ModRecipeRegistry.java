package RemoteCraftingTable.registry;

import necesse.engine.registries.RecipeTechRegistry;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipes;
import necesse.inventory.recipe.Recipe;

public class ModRecipeRegistry {
    public static void RegisterAll() {
        Recipes.registerModRecipe(
                new Recipe( "craftingremote",
                        RecipeTechRegistry.WORKSTATION,
                        new Ingredient[] {
                                new Ingredient("anylog", 10)}));
    }
}
