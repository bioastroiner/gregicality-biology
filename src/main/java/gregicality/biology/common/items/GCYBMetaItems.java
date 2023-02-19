package gregicality.biology.common.items;

import gregicality.biology.common.items.MicrobeItem.MicrobeOreValueItem;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;

// Here Lies declaration of our metaitem instance
public class GCYBMetaItems {
    public static StandardMetaItem METAITEMS;
    public static MicrobeItem MICROBES = new MicrobeItem((short) 0);
    /* Microbe Instances
     *  0 ~ 99 :   bc = bacteria
     *  100 ~ 199 :vi = virus
     *  200 ~ 299 :pr = protist
     * */
    // BC
    public static final MicrobeOreValueItem bc_ecoli = MICROBES.addOreDictItem(0, "ecoli", 0x153462, MicrobeItem.Bacilli)
            .builder(1,"Bacillus coli communis",false,6000,1,1);
    public static final MicrobeOreValueItem bc_rhizobium = MICROBES.addOreDictItem(1, "rhizobium", 0x82CD47, MicrobeItem.Bacilli)
            .builder(1,"Rhizobium leguminosarum",false,6200,1,1);
    public static final MicrobeOreValueItem bc_nitrosomanos = MICROBES.addOreDictItem(2, "nitrosomanos", 0x4FA095, MicrobeItem.Bacilli)
            .builder(1,"Nitrosomonas europaea",false,6200,1,1);
    // VR
    public static final MicrobeOreValueItem vr_retrovirus = MICROBES.addOreDictItem(100, "retrovirus", 0x964B00, MicrobeItem.VIRAL)
            .builder(3,"Retroviridae",true,20000,2,5);
    // PR
    public static final MicrobeOreValueItem pr_diatom = MICROBES.addOreDictItem(200, "diatom", 0x82CD47, MicrobeItem.PLASTID)
            .builder(1,"Bacillariophyceae Diatoma",false,1000,7,5); // phytoPlanktons
    public static final MicrobeOreValueItem pr_chlorophyte = MICROBES.addOreDictItem(201, "chlorophyte", 0x82CD47, MicrobeItem.PLASTID)
            .builder(1,"Chlorophyllophyceae",false,2300,10,5); // green algee
    public static final MicrobeOreValueItem pr_euglena = MICROBES.addOreDictItem(202, "euglena", 0x82CD47, MicrobeItem.PLASTID)
            .builder(1,"Euglena",false,2200,4,3);

    /* meta_item_1 */
    public static MetaItem<?>.MetaValueItem lam, lammle, lammle_lam;
    /* meta_microbe */
    public static MetaItem<?>.MetaValueItem bacteria;

    public static void initMetaItems() {
        METAITEMS = new StandardMetaItem();
        METAITEMS.setRegistryName("meta_item_1");
        //metaMicrobe1 = new MicrobeItem((short) 0); metaMicrobe1.setRegistryName("meta_microbe");
        MICROBES.setRegistryName("meta_microbe");
    }

    public static void initSubitems() {
        initMetaItem1();
    }

    private static void initMetaItem1() {
        // Lammels: ID 0-2
        lam = METAITEMS.addItem(0, "lam");
        lammle = METAITEMS.addItem(1, "lammle");
        lammle_lam = METAITEMS.addItem(2, "lammle_lam");
    }
}
