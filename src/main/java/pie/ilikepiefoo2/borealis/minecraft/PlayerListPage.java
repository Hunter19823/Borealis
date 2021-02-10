package pie.ilikepiefoo2.borealis.minecraft;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import pie.ilikepiefoo2.borealis.BorealisConfigHandler;
import pie.ilikepiefoo2.borealis.page.HTTPWebPage;
import pie.ilikepiefoo2.borealis.page.PageType;
import pie.ilikepiefoo2.borealis.tag.Tag;

/**
 * @author LatvianModder
 */
public class PlayerListPage extends HTTPWebPage {
    private final MinecraftServer server;

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
        Tag playerTable = body.table().addClass("player_table");
        for (ServerPlayerEntity player : server.getPlayerList().getPlayers())
        {
            String id = player.getUniqueID().toString().replace("-", "");
            Tag row = playerTable.tr();
            row.td().img("https://crafatar.com/avatars/" + id + "?size=16");
            row.td().a(player.getGameProfile().getName(), "https://mcuuid.net/?q=" + id);
        }
    }
}
