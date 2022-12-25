package gregicality.biology.loaders.recipe;

import gregicality.biology.api.unification.GCYBMaterials;
import gregicality.biology.common.blocks.GCYBMetaBlocks;
import gregicality.biology.common.blocks.LabWall;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;

public class LabCasingLoader {
    public static void init() {
        /*  Tier 1 Bio Casing  */
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .EUt(120)
                .duration(100)
                .input(MetaBlocks.CLEANROOM_CASING, 4)
                .input(OrePrefix.plate, Materials.CobaltBrass, 8)
                .input(OrePrefix.plate, Materials.BismuthBronze, 4)
                .fluidInputs(GregTechAPI.MaterialRegistry.get("polystyrene_sulfonate").getFluid(2304))
                .outputs(GCYBMetaBlocks.labWall.getItemVariant(LabWall.LabWallType.SAFE, 8))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .EUt(120)
                .duration(500)
                .input(OrePrefix.plate, Materials.ReinforcedEpoxyResin, 10)
                .input(OrePrefix.plate, Materials.CobaltBrass, 8)
                .input(OrePrefix.plate, Materials.BismuthBronze, 4)
                .fluidInputs(GregTechAPI.MaterialRegistry.get("polystyrene_sulfonate").getFluid(2880))
                .outputs(GCYBMetaBlocks.labWall.getItemVariant(LabWall.LabWallType.SAFE, 10))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        /*  Tier 2 Bio Casing  */
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .EUt(600)
                .duration(280)
                .input(OrePrefix.plate, Materials.ReinforcedEpoxyResin, 18)
                .input(OrePrefix.foil, Materials.Gold, 24)
                .input(OrePrefix.foil, Materials.Copper, 24)
                .input(OrePrefix.foil, Materials.Silver, 24)
                .input(OrePrefix.plateDouble, GregTechAPI.MaterialRegistry.get("polystyrene_sulfonate"), 32)
                .fluidInputs(GCYBMaterials.Cuproquin.getFluid(100))
                .outputs(GCYBMetaBlocks.labWall.getItemVariant(LabWall.LabWallType.NORMAL, 8))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        /*  Tier 3 Bio Casing  */
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .EUt(32000)
                .duration(360)
                .input(OrePrefix.pipeHugeFluid, Materials.Polybenzimidazole, 16)
                .input(OrePrefix.foil, Materials.Osmium, 64)
                .input(MetaItems.BLACKLIGHT)
                .input(OrePrefix.plateDouble, GregTechAPI.MaterialRegistry.get("polystyrene_sulfonate"), 64)
                .fluidInputs(GCYBMaterials.Cuproquin.getFluid(2000))
                .outputs(GCYBMetaBlocks.labWall.getItemVariant(LabWall.LabWallType.HAZARD, 64))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();
    }
}
