package gregicality.biology.common.item;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;

// Here Lies declaration of our metaitem instance
public class GCYBMetaItems {
    private static StandardMetaItem metaItem1;

    // Lammels and lams
    public static MetaItem<?>.MetaValueItem lam, lammle, lammle_lam;

    public static void initMetaItems(){
        metaItem1 = new StandardMetaItem();
        metaItem1.setRegistryName("meta_item_1");
    }

    public static void initSubitems() {
        initMetaItem1();
    }

    private static void initMetaItem1() {
        // Lammels: ID 0-2
        lam =metaItem1.addItem(0,"lam");
        lammle =metaItem1.addItem(1,"lammle");
        lammle_lam =metaItem1.addItem(2,"lammle_lam");
    }
}
