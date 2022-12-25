package gregicality.biology.common.items;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;

// Here Lies declaration of our metaitem instance
public class GCYBMetaItems {
    public static StandardMetaItem metaItem1;
    public static MicrobeItem metaMicrobe1 = new MicrobeItem((short) 0);
    /* Microbe Instances
     *  0 ~ 99 :   bc = bacteria
     *  100 ~ 199 :vi = virus
     *  200 ~ 299 :pr = protist
     * */
    // BC
    public static final MicrobeItem.MicrobeOreValueItem bc_ecoli = metaMicrobe1.addOreDictItem(0, "ecoli", 0x153462, MicrobeItem.SPIKY_CELL, MicrobeItem.Bacilli);
    public static final MicrobeItem.MicrobeOreValueItem bc_rhizobium = metaMicrobe1.addOreDictItem(1, "rhizobium", 0x82CD47, MicrobeItem.SMOOTH_CELL, MicrobeItem.Bacilli);
    public static final MicrobeItem.MicrobeOreValueItem bc_nitrosomanos = metaMicrobe1.addOreDictItem(2, "nitrosomanos", 0x4FA095, MicrobeItem.SMOOTH_CELL, MicrobeItem.Bacilli);
    // VR
    public static final MicrobeItem.MicrobeOreValueItem vr_retrovirus = metaMicrobe1.addOreDictItem(100, "retrovirus", 0x964B00, MicrobeItem.SMOOTH_CELL, MicrobeItem.VIRAL);
    // PR
    public static final MicrobeItem.MicrobeOreValueItem pr_diatom = metaMicrobe1.addOreDictItem(200, "diatom", 0x4FA095, MicrobeItem.SMOOTH_CELL, MicrobeItem.PLASTID); // phytoPlanktons
    public static final MicrobeItem.MicrobeOreValueItem pr_chlorophyte = metaMicrobe1.addOreDictItem(201, "chlorophyte", 0x4FA095, MicrobeItem.SPIKY_CELL, MicrobeItem.PLASTID); // green algee
    public static final MicrobeItem.MicrobeOreValueItem pr_euglena = metaMicrobe1.addOreDictItem(202, "euglena", 0x4FA095, MicrobeItem.SPIKY_CELL, MicrobeItem.PLASTID);

    /* meta_item_1 */
    public static MetaItem<?>.MetaValueItem lam, lammle, lammle_lam;
    /* meta_microbe */
    public static MetaItem<?>.MetaValueItem bacteria;

    public static void initMetaItems() {
        metaItem1 = new StandardMetaItem();
        metaItem1.setRegistryName("meta_item_1");
        //metaMicrobe1 = new MicrobeItem((short) 0); metaMicrobe1.setRegistryName("meta_microbe");
        metaMicrobe1.setRegistryName("meta_microbe");
    }

    public static void initSubitems() {
        initMetaItem1();
    }

    private static void initMetaItem1() {
        // Lammels: ID 0-2
        lam = metaItem1.addItem(0, "lam");
        lammle = metaItem1.addItem(1, "lammle");
        lammle_lam = metaItem1.addItem(2, "lammle_lam");
    }
}
