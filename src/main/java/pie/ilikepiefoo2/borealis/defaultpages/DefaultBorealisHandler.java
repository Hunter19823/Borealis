package pie.ilikepiefoo2.borealis.defaultpages;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pie.ilikepiefoo2.borealis.BorealisHomePageEvent;
import pie.ilikepiefoo2.borealis.BorealisPageEvent;
import pie.ilikepiefoo2.borealis.defaultpages.minecraft.*;
import pie.ilikepiefoo2.borealis.defaultpages.util.UtilPageHandler;
import pie.ilikepiefoo2.borealis.page.WebPage;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(Dist.DEDICATED_SERVER)
public class DefaultBorealisHandler {

    @SubscribeEvent
    public void onHomeEvent(BorealisHomePageEvent event)
    {
        MinecraftPageHandler.onHomeEvent(event);
        UtilPageHandler.onHomeEvent(event);
    }

    @SubscribeEvent
    public void onPageEvent(BorealisPageEvent event){
        MinecraftPageHandler.onPageEvent(event);
        UtilPageHandler.onPageEvent(event);
    }
}
