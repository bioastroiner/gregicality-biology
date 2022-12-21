package gregicality.biology;

import gregicality.biology.common.CommonProxy;
import gregicality.biology.common.block.GCYBMetaBlocks;
import gregicality.biology.common.item.GCYBMetaItems;
import gregicality.biology.common.metatileentities.GCYBMetaTileEntities;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = GregicalityBiology.MODID, name = GregicalityBiology.NAME, version = GregicalityBiology.VERSION,
        dependencies = "required-after:gregtech@(2.3.4,);" + "after:gcy_science")
public class GregicalityBiology {
    public static final String MODID = "gcyb";
    public static final String NAME = "Gregicality: Biology";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(modId = MODID, clientSide = "gregicality.biology.common.ClientProxy", serverSide = "gregicality.biology.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        //MinecraftForge.EVENT_BUS.register(this);
        GCYBMetaBlocks.init();
        GCYBMetaItems.initMetaItems();
        GCYBMetaTileEntities.init();
        proxy.preLoad();
    }
}