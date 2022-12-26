package gregicality.biology.common.blocks;

import gregtech.api.block.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public class LabWall extends VariantBlock<LabWall.LabWallType> {

    public LabWall() {
        super(Material.IRON);
        this.setTranslationKey("labwall");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(LabWallType.SAFE));
    }

    /* Specelized walls to contaminated different levels of labs */
    public enum LabWallType implements IStringSerializable {
        SAFE("safe"),
        NORMAL("normal"),
        HAZARD("hazard");

        private final String name;

        LabWallType(String name) {
            this.name = name;
        }

        @Nonnull
        public String getName() {
            return this.name;
        }
    }
}
