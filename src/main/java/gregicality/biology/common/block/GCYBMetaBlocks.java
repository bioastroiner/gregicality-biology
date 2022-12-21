package gregicality.biology.common.block;

import gregicality.biology.common.block.blocks.BioCasing;

public class GCYBMetaBlocks {
    public static BioCasing BIO_CASING;
    public static void init() {
        BIO_CASING = new BioCasing();
        BIO_CASING.setRegistryName("bio_casing");
    }
}
