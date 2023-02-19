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
    public static final MaterialIconSet CELL_ICON = new MaterialIconSet("cells"); // Rod/Spiral Shaped cell

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
    private final Map<Short, BioAttributeBuilder> BIO_ATTRIBUTES = new HashMap();

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
                    ModelBakery.registerItemVariants(this, resourceLocation);
                    alreadyRegistered.put(registrationKey, new ModelResourceLocation(resourceLocation, "inventory"));
                }

                ModelResourceLocation resourceLocation = alreadyRegistered.get(registrationKey);
                this.metaItemsModels.put(metaItem.getKey(), resourceLocation);
            }
        }

    }

    public MicrobeOreValueItem addOreDictItem(int id, String materialName, int rgb, OrePrefix orePrefix) {
        return this.addOreDictItem((short) id, materialName, rgb, CELL_ICON, orePrefix, "Microbe");
    }

    public MicrobeOreValueItem addOreDictItem(int id, String materialName, int rgb, MaterialIconSet materialIconSet, OrePrefix orePrefix) {
        return this.addOreDictItem((short) id, materialName, rgb, materialIconSet, orePrefix, "Microbe");
    }

    public MicrobeOreValueItem addOreDictItem(int id, String materialName, int materialRGB, MaterialIconSet materialIconSet, OrePrefix orePrefix, String chemicalFormula) {
        return new MicrobeOreValueItem((short) id, materialName, materialRGB, materialIconSet, orePrefix, chemicalFormula);
    }

    @Override
    public void addInformation(@Nonnull ItemStack itemStack, @Nullable World worldIn, @Nonnull List<String> lines, @Nonnull ITooltipFlag tooltipFlag) {
        BioAttributeBuilder attr = getMicrobeItemHelper(itemStack).getBioAttributes();
        lines.add(I18n.format("metaitem.microbe.tooltip.tier") + ": " + attr.getTier());
        lines.add(I18n.format("metaitem.microbe.tooltip.name") + ": " + attr.getScienteficName());
        lines.add(I18n.format("metaitem.microbe.tooltip.amount") + ": " + attr.getAmount());
        lines.add(I18n.format("metaitem.microbe.tooltip.pr") + ": " + attr.getProductionRate());
        lines.add(I18n.format("metaitem.microbe.tooltip.rp") + ": " + attr.getReproductionRate());
        lines.add(I18n.format("metaitem.microbe.tooltip.toxic") + ": " + attr.isToxic());
        super.addInformation(itemStack, worldIn, lines, tooltipFlag);
    }

    public MicrobeOreValueItem getMicrobeItemHelper(ItemStack stack) {
        MicrobeOreValueItem item = this.ITEMS.get((short) stack.getItemDamage());
        return item;
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

        public BioAttributeBuilder getBioAttributes() {
            return BIO_ATTRIBUTES.get(this.id);
        }

        /**
         * @param tier             the level of the Microbe determaines the Level of LAB
         *                         * 1 : safe or low contingency
         *                         * 2 : known desiese and high contingecy
         *                         * 3 : very toxic or unknown desieses
         * @param isToxic          is handling this microbe requires extra caution? the toxicity level depends on tier
         * @param amount           amounts determine how many cells are in a sample, usually smaller ones have higher amounts,
         *                         this effects the productivity attribute and the time to reproduce etc.
         *                         * amount 1 means in 1mb/L exists 1 unit of this cell
         * @param productionRate   how quickly it will reproduce it gets calculated in recipes,
         *                         keep in mind viruses don't produce or reproduce but with help of a host cell they do
         * @param reproductionRate how fast dose it reproduce, the speed gets calculated in recipes, and it's not Certain,
         *                         keep in mind viruses don't reproduce but this still effects how fast they use their host to do so
         */
        public MicrobeOreValueItem builder(int tier, String scienteficName, boolean isToxic, int amount, int productionRate, int reproductionRate) {
            BIO_ATTRIBUTES.put(this.id, new BioAttributeBuilder(tier, scienteficName, isToxic, amount, productionRate, reproductionRate));
            return this;
        }
    }

    private class BioAttributeBuilder {

        /* FIELDS */
        private final int tier;
        private final String scienteficName;
        private final boolean isToxic;
        private final int amount;

        private final int productionRate;

        private final int reproductionRate;

        private BioAttributeBuilder(int tier, String scienteficName, Boolean isToxic, int amount, int productionRate, int reproductionRate) {
            this.tier = tier;
            this.scienteficName = scienteficName;
            this.isToxic = isToxic;
            this.amount = amount;
            this.productionRate = productionRate;
            this.reproductionRate = reproductionRate;
        }

        /* GETTERS */
        public int getTier() {
            return tier;
        }

        public String getScienteficName() {
            return scienteficName;
        }

        public boolean isToxic() {
            return isToxic;
        }

        public int getAmount() {
            return amount;
        }

        public int getProductionRate() {
            return productionRate;
        }

        public int getReproductionRate() {
            return reproductionRate;
        }
    }
}
