package pie.ilikepiefoo2.borealis.defaultpages.util;

import net.minecraftforge.fml.ModList;
import pie.ilikepiefoo2.borealis.BorealisConfigHandler;
import pie.ilikepiefoo2.borealis.BorealisHomePageEvent;
import pie.ilikepiefoo2.borealis.BorealisPageEvent;
import pie.ilikepiefoo2.borealis.defaultpages.minecraft.ModPage;
import pie.ilikepiefoo2.borealis.page.HomePageEntry;
import pie.ilikepiefoo2.borealis.WebCache;

import java.util.Arrays;
import java.util.HashSet;

public class UtilPageHandler {
    public static void onHomeEvent(BorealisHomePageEvent event)
    {
        event.add(new HomePageEntry("Mod List", ModListPage.URI, "https://i.imgur.com/yQNrfg7.png"));
    }


    public static void onPageEvent(BorealisPageEvent event){
        if (event.checkPath(ModListPage.URI, "*"))
        {
            HashSet<String> set = new HashSet(Arrays.asList(BorealisConfigHandler.COMMON.blacklistedMods.get()));

            if (!set.contains(event.getSplitUri()[1]))
            {
                if (ModList.get().isLoaded(event.getSplitUri()[1]))
                {
                    event.cachePage(ModListPage.URI+"/"+event.getSplitUri()[1], s -> new ModPage(ModList.get().getModContainerById(event.getSplitUri()[1]).get().getModInfo()).neverDirty());
                }
            }
        }
        else if (event.checkPath(ModListPage.URI))
        {
            event.cachePage(ModListPage.URI, s -> new ModListPage(new HashSet<>(BorealisConfigHandler.COMMON.blacklistedMods.get())).neverDirty());
        }

    }
}
