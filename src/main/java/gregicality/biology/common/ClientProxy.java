package gregicality.biology.common;

import gregicality.biology.common.blocks.GCYBMetaBlocks;
import gregicality.biology.common.items.GCYBMetaItems;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GCYBMetaBlocks.registerItemModels();
        GCYBMetaItems.MICROBES.registerModels();
    }

    public void preLoad() {
        super.preLoad();
    }
}
