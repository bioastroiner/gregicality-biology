package gregicality.biology.common.items;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableList;
import gnu.trove.map.hash.TIntObjectHashMap;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.SmallDigits;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MicrobeItem extends StandardMetaItem {

    /* General Shape of Microbe */
    public static final MaterialIconSet SPIKY_CELL = new MaterialIconSet("spiky"); // Rod/Spiral Shaped cell
    public static final MaterialIconSet SMOOTH_CELL = new MaterialIconSet("smooth"); // Dot Shaped Cell

    /* Specific Shape of Microbe */
    public static final OrePrefix SPIRAL = new OrePrefix("spiral", -1L, null, new MaterialIconType("spiral"), 0L, null); // Spiral Shaped cell
    public static final OrePrefix Bacilli = new OrePrefix("bacilli", -1L, null, new MaterialIconType("bacilli"), 0L, null); // Rod-Shaped
    public static final OrePrefix COCCI = new OrePrefix("cocci", -1L, null, new MaterialIconType("cocci"), 0L, null); // Spherical Shaped / Spore
    public static final OrePrefix VIRAL = new OrePrefix("viral", -1L, null, new MaterialIconType("viral"), 0L, null); // Virus
    public static final OrePrefix AMOEBA = new OrePrefix("amoeba", -1L, null, new MaterialIconType("amoeba"), 0L, null); // Animal-like Cell & non Plastid/Fungi Proteist
    public static final OrePrefix PLASTID = new OrePrefix("plastid", -1L, null, new MaterialIconType("plastid"), 0L, null); // Plant & Algea Cell

    private static final List<MaterialIconType> DISALLOWED_TYPES;
    private static final ModelResourceLocation MISSING_LOCATION;

    static {
        DISALLOWED_TYPES = ImmutableList.of(MaterialIconType.block, MaterialIconType.foilBlock, MaterialIconType.wire, MaterialIconType.ore, MaterialIconType.frameGt, MaterialIconType.pipeHuge, MaterialIconType.pipeLarge, MaterialIconType.pipeSide, MaterialIconType.pipeSmall, MaterialIconType.pipeMedium, MaterialIconType.pipeTiny);
        MISSING_LOCATION = new ModelResourceLocation("builtin/missing", "inventory");
    }

    public final Map<String, String> OREDICT_TO_FORMULA = new HashMap();
    private final Map<Short, MicrobeOreValueItem> ITEMS = new HashMap();
    private final Map<Short, MicrobeProperties> PROPERTIES = new HashMap();

    public MicrobeItem(short metaItemOffset) {
        super(metaItemOffset);
    }

    public void registerSubItems() {
        Iterator var1 = this.ITEMS.values().iterator();

        while (var1.hasNext()) {
            MicrobeOreValueItem item = (MicrobeOreValueItem) var1.next();
            this.addItem(item.id, item.getName());
            OreDictUnifier.registerOre(new ItemStack(this, 1, item.id), item.getOre());
        }

    }

    @SideOnly(Side.CLIENT)
    protected int getColorForItemStack(ItemStack stack, int tintIndex) {
        if (tintIndex == 0) {
            MicrobeOreValueItem item = this.ITEMS.get((short) stack.getItemDamage());
            return item == null ? 16777215 : item.color;
        } else {
            return super.getColorForItemStack(stack, tintIndex);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerModels() {
        TIntObjectHashMap<ModelResourceLocation> alreadyRegistered = new TIntObjectHashMap();
        Iterator var2 = this.ITEMS.entrySet().iterator();

        while (var2.hasNext()) {
            Map.Entry<Short, MicrobeOreValueItem> metaItem = (Map.Entry) var2.next();
            OrePrefix prefix = (metaItem.getValue()).orePrefix;
            MaterialIconSet materialIconSet = (metaItem.getValue()).iconSet;
            if (prefix.materialIconType != null && !DISALLOWED_TYPES.contains(prefix.materialIconType)) {
                int registrationKey = prefix.id * 1000 + materialIconSet.id;
                if (!alreadyRegistered.containsKey(registrationKey)) {
                    prefix.materialIconType.getItemModelPath(materialIconSet);
                    ResourceLocation resourceLocation = prefix.materialIconType.getItemModelPath(materialIconSet);
                    ModelBakery.registerItemVariants(this, new ResourceLocation[]{resourceLocation});
                    alreadyRegistered.put(registrationKey, new ModelResourceLocation(resourceLocation, "inventory"));
                }

                ModelResourceLocation resourceLocation = alreadyRegistered.get(registrationKey);
                this.metaItemsModels.put(metaItem.getKey(), resourceLocation);
            }
        }

    }

    public MicrobeOreValueItem addOreDictItem(int id, String materialName, int rgb, MaterialIconSet materialIconSet, OrePrefix orePrefix) {
        return this.addOreDictItem((short) id, materialName, rgb, materialIconSet, orePrefix, "Microbe");
    }

    public MicrobeOreValueItem addOreDictItem(int id, String materialName, int materialRGB, MaterialIconSet materialIconSet, OrePrefix orePrefix, String chemicalFormula) {
        return new MicrobeOreValueItem((short) id, materialName, materialRGB, materialIconSet, orePrefix, chemicalFormula);
    }

    @Override
    public void addInformation(@Nonnull ItemStack itemStack, @Nullable World worldIn, @Nonnull List<String> lines, @Nonnull ITooltipFlag tooltipFlag) {
        if (ITEMS != null && PROPERTIES != null)
            for (int i = 0; i < ITEMS.size(); i++) {
                ItemStack itemStack1 = ITEMS.get(i).getItemStack();
                lines.add(I18n.format("metaitem.microbe.tier.tooltip") + ": " + PROPERTIES.get(i).tier);
                lines.add(I18n.format("metaitem.microbe.type.tooltip") + ": ");
                lines.add(I18n.format("metaitem.microbe.size.tooltip") + ": ");
                lines.add(I18n.format("metaitem.microbe.note.tooltip") + ": ");
            }
        super.addInformation(itemStack, worldIn, lines, tooltipFlag);
    }

    public class MicrobeOreValueItem {
        private final String name;
        private final int color;
        private final MaterialIconSet iconSet;
        private final short id;
        private final OrePrefix orePrefix;
        protected String chemicalFormula;

        /**
         * @param id
         * @param nameIn Scientefic Name of the MicroOrganism
         */
        private MicrobeOreValueItem(short id, String nameIn, int color, MaterialIconSet iconSet, OrePrefix orePrefix, String chemicalFormula) {
            this.id = id;
            this.name = nameIn;
            this.color = color;
            this.iconSet = iconSet;
            this.orePrefix = orePrefix;
            this.chemicalFormula = chemicalFormula;
            ITEMS.put(this.id, this);
            OREDICT_TO_FORMULA.put(this.getOre(), this.calculateChemicalFormula(chemicalFormula));
        }

        public String getOre() {
            return this.orePrefix.name() + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, this.name);
        }

        public ItemStack getItemStack(int amount) {
            ItemStack stack = OreDictUnifier.get(this.getOre());
            stack.setCount(amount);
            return stack;
        }

        public ItemStack getItemStack() {
            return this.getItemStack(1);
        }

        public String getName() {
            return this.name;
        }

        protected String calculateChemicalFormula(String unformattedFormula) {
            StringBuilder sb = new StringBuilder();
            if (unformattedFormula != null && !unformattedFormula.isEmpty()) {
                char[] var3 = unformattedFormula.toCharArray();
                int var4 = var3.length;

                for (int var5 = 0; var5 < var4; ++var5) {
                    char c = var3[var5];
                    if (Character.isDigit(c)) {
                        sb.append(SmallDigits.toSmallDownNumbers(Character.toString(c)));
                    } else {
                        sb.append(c);
                    }
                }
            }

            return sb.toString();
        }

        public String getFormula() {
            return this.chemicalFormula;
        }

        public int getColor() {
            return this.color;
        }

        public MicrobeProperties getMicrobeProperties() {
            return PROPERTIES.get(this.id);
        }

        public MicrobeOreValueItem setMicrobeProperties(int tier) {
            PROPERTIES.put(this.id, new MicrobeProperties(tier));
            return this;
        }
    }

    private class MicrobeProperties {
        /**
         * @param tier
         * 1 : safe
         * 2 : contaigus
         * 3 : toxic
         * 4 : hazardous
         */
        private final int tier;
        //private final String leanage;

        private MicrobeProperties(int tier) {
            this.tier = tier;
        }
    }
}
