package gregicality.biology.loaders.recipe;

import gregicality.biology.api.unification.GCYBMaterials;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;

public class CuproquinChain {
    public static void init() {
        RecipeMaps.LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Chlorobenzene.getFluid(1000))
                .fluidInputs(Materials.Ammonia.getFluid(1000))
                .fluidInputs(Materials.SulfuricAcid.getFluid(1000))
                .fluidInputs(Materials.Nitrobenzene.getFluid(1000))
                .notConsumable(OrePrefix.dust, Materials.Pyrite)
                .fluidOutputs(GCYBMaterials.Quinoline.getFluid(2000), Materials.HydrochloricAcid.getFluid(1000))
                .EUt(200)
                .duration(100)
                .notConsumable(new IntCircuitIngredient(2))
                .buildAndRegister();
        RecipeMaps.LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(GCYBMaterials.Quinoline.getFluid(2000))
                .fluidInputs(Materials.SulfuricAcid.getFluid(1000))
                .fluidInputs(Materials.Nitrobenzene.getFluid(1000))
                .input(OrePrefix.dust, Materials.SodiumHydroxide)
                .fluidOutputs(GCYBMaterials.Cuproquin.getFluid(1000))
                .EUt(300)
                .duration(50)
                .buildAndRegister();
    }
}
