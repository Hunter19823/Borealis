package pie.ilikepiefoo2.borealis.defaultpages.minecraft;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import pie.ilikepiefoo2.borealis.BorealisConfigHandler;
import pie.ilikepiefoo2.borealis.page.JsonWebPage;
import pie.ilikepiefoo2.borealis.page.PageType;

/**
 * @author LatvianModder
 */
public class WorldInfoJson extends JsonWebPage {
    private final MinecraftServer server;
    public static final String URI = "world_info.json";

    public WorldInfoJson(MinecraftServer server)
    {
        this.server = server;
    }

    @Override
    public PageType getPageType()
    {
        return BorealisConfigHandler.COMMON.worldInfoJSON.get();
    }

    @Override
    public JsonElement getJson()
    {
        JsonObject json = new JsonObject();

        server.getAllLevels().forEach(
                serverWorld -> json.add(serverWorld.dimension().location().toString(),getWorldJSON(serverWorld))
        );
        return json;
    }

    public static JsonObject getWorldJSON( ServerLevel level)
    {
        JsonObject json = new JsonObject();
        json.addProperty("local_time", level.getDayTime());
        json.addProperty("total_time", level.getGameTime());
        json.addProperty("daytime", level.isDay());
        json.addProperty("raining", level.isRaining());
        json.addProperty("thundering", level.isThundering());
        // Get Moon Phase is client only.

        //json.addProperty("moon_phase", level.getMoonPhase());
        return json;
    }
}
