package pie.ilikepiefoo2.borealis.defaultpages;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pie.ilikepiefoo2.borealis.BorealisHomePageEvent;
import pie.ilikepiefoo2.borealis.BorealisPageEvent;
import pie.ilikepiefoo2.borealis.defaultpages.minecraft.*;
import pie.ilikepiefoo2.borealis.defaultpages.util.UtilPageHandler;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

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
    public static Map<String, String> splitQuery(URI url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
}
