package pie.ilikepiefoo2.notaurora.page;


import dev.latvian.mods.aurora.page.HTTPWebPage;
import dev.latvian.mods.aurora.page.HomePageEntry;
import dev.latvian.mods.aurora.tag.Style;
import dev.latvian.mods.aurora.tag.Tag;
import net.minecraftforge.common.MinecraftForge;
import pie.ilikepiefoo2.notaurora.AuroraHomePageEvent;
import pie.ilikepiefoo2.notaurora.NotAuroraServer;

/**
 * @author LatvianModder
 */
public class HomePage extends HTTPWebPage
{
    public final NotAuroraServer server;

    public HomePage(NotAuroraServer s)
    {
        server = s;
    }

    @Override
    public String getTitle()
    {
        return "Aurora";
    }

    @Override
    public String getDescription()
    {
        return "Index";
    }

    @Override
    public boolean addBackButton()
    {
        return false;
    }

    @Override
    public void head(Tag head)
    {
        super.head(head);
        Style s = head.style();
        s.add("li").set("font-size", "1.5em").set("margin-bottom", "0.2em");
        s.add("li img").set("height", "1.5em").set("vertical-align", "middle").set("margin-right", "0.2em");
        s.add("ol").set("list-style", "none");
    }

    @Override
    public void body(Tag body)
    {
        HomePageEntry entry = new HomePageEntry(getTitle(), "", getIcon());
        MinecraftForge.EVENT_BUS.post(new AuroraHomePageEvent(server, entry));
        entry.entries.sort(null);
        addEntries(body.ol().style("width", "max-content").style("margin", "auto"), entry, "");
    }

    private void addEntries(Tag list, HomePageEntry entry, String u)
    {
        for (HomePageEntry e : entry.entries)
        {
            Tag li = list.li();

            if (!e.icon.isEmpty())
            {
                li.img(e.icon);
            }

            li.a(e.title, u + "/" + e.url);

            if (!e.entries.isEmpty())
            {
                addEntries(list.ol(), e, u + "/" + e.url);
            }
        }
    }
}