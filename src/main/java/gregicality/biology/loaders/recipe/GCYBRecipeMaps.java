package gregicality.biology.loaders.recipe;

import gregicality.science.client.render.GCYSGuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.sound.GTSounds;

public class GCYBRecipeMaps {
    public static final RecipeMap<SimpleRecipeBuilder> MICROSCOPE_RECIPES = new RecipeMap<>("microscope", 1, 2, 1, 4, 1, 1, 1, 1, new SimpleRecipeBuilder(), false)
            .setSound(GTSounds.ELECTROLYZER)
            .setProgressBar(GCYSGuiTextures.PROGRESS_BAR_NANOSCALE, ProgressWidget.MoveType.CIRCULAR);
}
