package pie.ilikepiefoo2.borealis.minecraft;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import pie.ilikepiefoo2.borealis.BorealisConfigHandler;
import pie.ilikepiefoo2.borealis.BorealisHomePageEvent;
import pie.ilikepiefoo2.borealis.BorealisPageEvent;
import pie.ilikepiefoo2.borealis.page.HomePageEntry;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.net.URLDecoder.decode;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(Dist.DEDICATED_SERVER)
public class BorealisMinecraftHandler {

    @SubscribeEvent
    public void onHomeEvent(BorealisHomePageEvent event)
    {
        event.add(new HomePageEntry("Mod List", "modlist", "https://i.imgur.com/yQNrfg7.png"));
        event.add(new HomePageEntry("Minecraft", "minecraft", "https://i.imgur.com/1aRpzK0.png")
                .add(new HomePageEntry("Online Players", "online_players", "https://i.imgur.com/a5dkvFu.png"))
                .add(new HomePageEntry("Online Players API", "online_players.json", "https://i.imgur.com/a5dkvFu.png"))
                .add(new HomePageEntry("World Info API", "world_info.json", "https://i.imgur.com/OVxZy1w.png"))
        );
    }

    @SubscribeEvent
    public void onPageEvent(BorealisPageEvent event){
        if (event.checkPath("modlist", "*"))
        {
            HashSet<String> set = new HashSet(Arrays.asList(BorealisConfigHandler.COMMON.blacklistedMods.get()));

            if (!set.contains(event.getSplitUri()[1]))
            {
                if (ModList.get().isLoaded(event.getSplitUri()[1]))
                {
                    event.returnPage(new ModPage(ModList.get().getModContainerById(event.getSplitUri()[1]).get().getModInfo()));
                }
            }
        }else if (event.checkPath("json", "*"))
        {
            LogManager.getLogger().info(event.getUri());
            try {
                URI uri = new URI(event.getUri());
                Map<String, String> parameters = splitQuery(uri);
                if(parameters.containsKey("json")){
                    String decodeJSON = URLDecoder.decode(parameters.get("json"),StandardCharsets.UTF_8.toString());
                    JsonElement object = new JsonParser().parse(decodeJSON);
                    event.returnPage(new JSONTreeViewPage(object));
                }else{
                    // TODO add missing URL parameter page.
                    LogManager.getLogger().error("Missing JSON parameter");
                }
            } catch (URISyntaxException e) {
                LogManager.getLogger().error("Invalid URI");
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                LogManager.getLogger().error("Invalid Characters in URI");
                e.printStackTrace();
            } catch (NullPointerException e) {
                LogManager.getLogger().error("Null Pointer");
                e.printStackTrace();
            }
        }
        else if (event.checkPath("json"))
        {
            event.returnPage(new JSONPage());
        }
        else if (event.checkPath("modlist"))
        {
            event.returnPage(new ModListPage(new HashSet<>(BorealisConfigHandler.COMMON.blacklistedMods.get())));
        }
        else if (event.checkPath("minecraft", "online_players"))
        {
            event.returnPage(new PlayerListPage(event.getBorealisServer().getMinecraftServer()));
        }
        else if (event.checkPath("minecraft", "online_players.json"))
        {
            event.returnPage(new PlayerListJson(event.getBorealisServer().getMinecraftServer()));
        }
        else if (event.checkPath("minecraft", "world_info.json"))
        {
            LogManager.getLogger().info("World JSON accessed");
            event.returnPage(new WorldInfoJSON(event.getBorealisServer().getMinecraftServer()));
        }
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
