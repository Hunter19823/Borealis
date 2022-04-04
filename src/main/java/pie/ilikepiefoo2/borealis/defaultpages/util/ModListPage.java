package pie.ilikepiefoo2.borealis.defaultpages.util;


import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModInfo;
import pie.ilikepiefoo2.borealis.BorealisConfigHandler;
import pie.ilikepiefoo2.borealis.page.HTTPWebPage;
import pie.ilikepiefoo2.borealis.page.PageType;
import pie.ilikepiefoo2.borealis.tag.Style;
import pie.ilikepiefoo2.borealis.tag.Tag;

import java.util.Set;

import static pie.ilikepiefoo2.borealis.defaultpages.minecraft.MinecraftPageHandler.addTitleIcon;

/**
 * @author LatvianModder
 */
public class ModListPage extends HTTPWebPage
{
    private final Set<String> excludedMods;
    public static final String URI = "modlist";

    public ModListPage(Set<String> set)
    {
        excludedMods = set;
    }

    @Override
    public String getTitle()
    {
        return "Minecraft";
    }

    @Override
    public String getDescription()
    {
        return "Mod List";
    }

    @Override
    public PageType getPageType()
    {
        return BorealisConfigHandler.COMMON.modListPage.get();
    }

    @Override
    public void head(Tag head)
    {
        super.head(head);
        Style s = head.style();
        s.add("span.num").set("margin-right", "0.8em");
    }

    @Override
    public void body(Tag body)
    {
        addTitleIcon(body);
        Tag table = body.table();
        Tag row = table.tr();
        row.th().text("Mod List");
        row.th().text("Version");

        int i = 0;


        for (IModInfo info : ModList.get().getMods())
        {
            if (!excludedMods.contains(info.getModId()))
            {
                row = table.tr();
                Tag t = row.td();
                t.span(String.valueOf(++i), "num");
                t.a(info.getNamespace(), URI+"/" + info.getModId());
                row.td().text(info.getVersion());
            }
        }
    }
}