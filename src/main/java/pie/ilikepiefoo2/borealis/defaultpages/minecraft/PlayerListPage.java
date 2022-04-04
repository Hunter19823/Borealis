package pie.ilikepiefoo2.borealis.defaultpages.minecraft;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import pie.ilikepiefoo2.borealis.BorealisConfigHandler;
import pie.ilikepiefoo2.borealis.page.HTTPWebPage;
import pie.ilikepiefoo2.borealis.page.PageType;
import pie.ilikepiefoo2.borealis.tag.Tag;

import static pie.ilikepiefoo2.borealis.defaultpages.minecraft.MinecraftPageHandler.addTitleIcon;

/**
 * @author LatvianModder
 */
public class PlayerListPage extends HTTPWebPage {
    private final MinecraftServer server;
    public static final String URI = "player_list";

    public PlayerListPage(MinecraftServer server)
    {
        this.server = server;
    }

    @Override
    public String getTitle()
    {
        return "Minecraft";
    }

    @Override
    public String getDescription()
    {
        return "Player List";
    }

    @Override
    public PageType getPageType()
    {
        return BorealisConfigHandler.COMMON.playerListPage.get();
    }

    @Override
    public void body(Tag body)
    {
        addTitleIcon(body);
        Tag playerTable = body.table().addClass("player_table");
        for (ServerPlayer player : server.getPlayerList().getPlayers())
        {
            String id = player.getStringUUID().replace("-", "");
            Tag row = playerTable.tr();
            row.td().img("https://crafatar.com/avatars/" + id + "?size=16");
            row.td().a(player.getGameProfile().getName(), "https://mcuuid.net/?q=" + id);
        }
    }
}
