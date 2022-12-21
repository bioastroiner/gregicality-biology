package gregicality.biology.common;

import gregicality.biology.GregicalityBiology;
import gregicality.biology.api.unification.GCYBMaterials;
import gregtech.api.GregTechAPI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = GregicalityBiology.MODID)
public class GCYBEventHandlers {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(GregTechAPI.MaterialEvent event) {
        GCYBMaterials.init();
    }
}
