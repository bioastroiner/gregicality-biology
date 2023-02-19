package gregicality.biology.common.items;

import net.minecraft.item.ItemStack;

public class ItemHelper {
    public MicrobeItem.MicrobeOreValueItem getMicrobeItemHelper(ItemStack stack){
        return GCYBMetaItems.MICROBES.getMicrobeItemHelper(stack);
    }
}
