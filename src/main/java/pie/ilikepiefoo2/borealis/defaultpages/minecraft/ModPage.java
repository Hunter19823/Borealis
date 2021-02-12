package pie.ilikepiefoo2.borealis.defaultpages.minecraft;


import net.minecraftforge.forgespi.language.IModInfo;
import pie.ilikepiefoo2.borealis.BorealisConfigHandler;
import pie.ilikepiefoo2.borealis.page.HTTPWebPage;
import pie.ilikepiefoo2.borealis.page.PageType;
import pie.ilikepiefoo2.borealis.tag.Tag;

import static pie.ilikepiefoo2.borealis.defaultpages.minecraft.MinecraftPageHandler.addTitleIcon;

/**
 * @author LatvianModder
 */
public class ModPage extends HTTPWebPage
{
    private final IModInfo mod;

    public ModPage(IModInfo m)
    {
        mod = m;
    }

    @Override
    public String getTitle()
    {
        return "Minecraft";
    }

    @Override
    public String getDescription()
    {
        return "Mod "+mod.getDisplayName();
    }

    @Override
    public PageType getPageType()
    {
        return BorealisConfigHandler.COMMON.modListPage.get();
    }

    @Override
    public void body(Tag body)
    {
        addTitleIcon(body);
        body.h1(mod.getDisplayName()).text(" ").span(mod.getVersion().toString(), "other");

        if (!mod.getDescription().isEmpty())
        {
            body.h3("").paired("i", mod.getDescription());
        }

        if (mod.getUpdateURL() != null)
        {
            body.h3("").a(mod.getUpdateURL().toString(), mod.getUpdateURL().toString());
        }
        if (mod.getModProperties().containsKey("authors"))
        {
            body.h3("Author(s): ").span(mod.getModProperties().get("authors").toString(), "other");
        }
    }
}