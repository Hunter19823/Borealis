package pie.ilikepiefoo2.borealis.defaultpages.minecraft;

import pie.ilikepiefoo2.borealis.BorealisHomePageEvent;
import pie.ilikepiefoo2.borealis.BorealisPageEvent;
import pie.ilikepiefoo2.borealis.page.HomePageEntry;
import pie.ilikepiefoo2.borealis.tag.Tag;

public class MinecraftPageHandler {
    public static final String URI = "minecraft";

    public static void onHomeEvent(BorealisHomePageEvent event)
    {
        event.add(new HomePageEntry("Minecraft", URI, "https://i.imgur.com/1aRpzK0.png")
                .add(new HomePageEntry("Online Players", PlayerListPage.URI, "https://i.imgur.com/a5dkvFu.png"))
                .add(new HomePageEntry("Online Players API", PlayerListJson.URI, "https://i.imgur.com/a5dkvFu.png"))
                .add(new HomePageEntry("World Info API", WorldInfoJson.URI, "https://i.imgur.com/OVxZy1w.png"))
        );
    }

    public static void onPageEvent(BorealisPageEvent event){
        if (event.checkPath(URI, PlayerListPage.URI))
        {
            event.returnPage(new PlayerListPage(event.getBorealisServer().getMinecraftServer()));
        }
        else if (event.checkPath(URI, PlayerListJson.URI))
        {
            event.returnPage(new PlayerListJson(event.getBorealisServer().getMinecraftServer()));
        }
        else if (event.checkPath(URI, WorldInfoJson.URI))
        {
            event.returnPage(new WorldInfoJson(event.getBorealisServer().getMinecraftServer()));
        }
    }

    public static void addTitleIcon(Tag body)
    {
        body.img("https://media.forgecdn.net/avatars/339/543/637479953862421663.png").style("height", "7em");
        body.br();
    }
}
