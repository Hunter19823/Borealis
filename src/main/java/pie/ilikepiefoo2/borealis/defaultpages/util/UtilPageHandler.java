package pie.ilikepiefoo2.borealis.defaultpages.util;

import pie.ilikepiefoo2.borealis.BorealisConfigHandler;
import pie.ilikepiefoo2.borealis.BorealisHomePageEvent;
import pie.ilikepiefoo2.borealis.BorealisPageEvent;
import pie.ilikepiefoo2.borealis.defaultpages.minecraft.ModListPage;
import pie.ilikepiefoo2.borealis.page.HomePageEntry;

import java.util.HashSet;

public class UtilPageHandler {

    public static void onHomeEvent(BorealisHomePageEvent event)
    {
        event.add(new HomePageEntry("JSON Viewer", "json", "https://i.imgur.com/a5dkvFu.png"));
    }



    public static void onPageEvent(BorealisPageEvent event){
        if (event.checkPath("json"))
        {
            event.returnPage(new JSONPage());
        }
    }
}
