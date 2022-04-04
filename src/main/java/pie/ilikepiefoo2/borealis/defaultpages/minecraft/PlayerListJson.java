package pie.ilikepiefoo2.borealis.defaultpages.minecraft;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import pie.ilikepiefoo2.borealis.BorealisConfigHandler;
import pie.ilikepiefoo2.borealis.page.JsonWebPage;
import pie.ilikepiefoo2.borealis.page.PageType;

/**
 * @author LatvianModder
 */
public class PlayerListJson extends JsonWebPage
{
    private final MinecraftServer server;
    public static final String URI = "player_list.json";

    public PlayerListJson(MinecraftServer s)
    {
        server = s;
    }


    @Override
    public PageType getPageType()
    {
        return BorealisConfigHandler.COMMON.playerListJSON.get();
    }

    @Override
    public JsonElement getJson()
    {
        JsonObject json = new JsonObject();
        json.addProperty("max_players", server.getMaxPlayers());

        JsonArray players = new JsonArray();

        for (ServerPlayer player : server.getPlayerList().getPlayers())
        {
            JsonObject o = new JsonObject();
            o.addProperty("name", player.getGameProfile().getName());
            o.addProperty("uuid", player.getStringUUID());
            players.add(o);
        }

        json.add("players", players);
        return json;
    }
}