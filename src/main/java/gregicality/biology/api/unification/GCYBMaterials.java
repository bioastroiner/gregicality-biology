package gregicality.biology.api.unification;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconSet;

public class GCYBMaterials {
    // Copper 8-hydroxyquinoline
    // (1) heating quinoline and fuming sulfuric acid to react,
    // hydrolyzing to obtain quinolyl-8-sulfonic acid,
    // and heating the quinolyl-8-sulfonic acid and s
    // olid sodium hydroxide to react to obtain 8-hydroxyquinoline;
    // and (2) completely dissolve sodium hydroxide and the 8-hydroxyquinoline prepared in the step (1) in water,
    // and adding anhydrous cupric sulfate to react to obtain the copper 8-hydroxyquinoline.
    public static Material Cuproquin;
    public static Material Quinoline;

    public static void init() {
        // Is a very potent antibacterial
        Cuproquin = new Material.Builder(25000, "cuprouin")
                .fluid()
                .colorAverage()
                .iconSet(MaterialIconSet.SHINY)
                .components(Materials.Carbon, 18,
                        Materials.Hydrogen, 18,
                        Materials.Copper, 1,
                        Materials.Nitrogen, 2,
                        Materials.Oxygen, 2)
                .build();
        Quinoline = new Material.Builder(25001, "quinoline")
                .fluid()
                .colorAverage()
                .components(Materials.Carbon, 9,
                        Materials.Hydrogen, 7,
                        Materials.Nitrogen, 1)
                .build();

    }
}
