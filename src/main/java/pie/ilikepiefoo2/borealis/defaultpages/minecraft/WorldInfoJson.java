package pie.ilikepiefoo2.borealis.defaultpages.minecraft;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;
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
        server.getWorlds().forEach(
                serverWorld -> json.add(serverWorld.getDimensionKey().getLocation().toString(),getWorldJSON(serverWorld))
        );
        return json;
    }

    public static JsonObject getWorldJSON(ServerWorld world)
    {
        JsonObject json = new JsonObject();
        json.addProperty("local_time", world.getDayTime());
        json.addProperty("total_time", world.getGameTime());
        json.addProperty("daytime", world.isDaytime());
        json.addProperty("raining", world.isRaining());
        json.addProperty("thundering", world.isThundering());
        // Get Moon Phase is client only.

        //json.addProperty("moon_phase", world.getMoonPhase());
        return json;
    }
}
