package gregicality.biology.common;

import gregicality.biology.GregicalityBiology;
import gregicality.biology.common.block.GCYBMetaBlocks;
import gregicality.biology.common.item.GCYBMetaItems;
import gregicality.biology.loaders.recipe.GCYBRecipeLoader;
import gregtech.api.block.VariantItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = GregicalityBiology.MODID)
public class CommonProxy {
    @SubscribeEvent
    public static void registerItems(@Nonnull RegistryEvent.Register<Item> event) {
        GCYBMetaItems.initSubitems();
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(createItemBlock(GCYBMetaBlocks.BIO_CASING, VariantItemBlock::new));
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = (ItemBlock) producer.apply(block);
        itemBlock.setRegistryName((ResourceLocation) Objects.requireNonNull(block.getRegistryName()));
        return itemBlock;
    }

    @SubscribeEvent()
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {

        // Main recipe registration
        // This is called AFTER GregTech registers recipes, so
        // anything here is safe to call removals in
        GCYBRecipeLoader.init();
    }

    public void preLoad() {
    }

    public void onLoad() {
    }

    public void onPostLoad() {
    }

}
