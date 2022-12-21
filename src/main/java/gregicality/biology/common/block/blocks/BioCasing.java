package gregicality.biology.common.block.blocks;

import gregtech.api.block.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public class BioCasing extends VariantBlock<BioCasing.BioCasingType> {

    public BioCasing() {
        super(Material.IRON);
        this.setTranslationKey("bio_casing");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(BioCasingType.BIO1));
    }

    public static enum BioCasingType implements IStringSerializable {

        // Brass Casing Doped in Copper 8-hydroxyquinoline
        BIO1("antibacterial_casing"),

        // PolyStyrin Sulfonite (exists in GCY:S)
        BIO2("antiviral_casing"),

        // Phenylmercuric borate
        BIO3("biohazard_casing");

        private final String name;

        private BioCasingType(String name) {
            this.name = name;
        }

        @Nonnull
        public String getName() {
            return this.name;
        }
    }
}
