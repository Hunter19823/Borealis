package pie.ilikepiefoo2.borealis;


import net.minecraftforge.eventbus.api.Event;
import pie.ilikepiefoo2.borealis.page.HomePageEntry;

/**
 * @author LatvianModder
 */
public class BorealisHomePageEvent extends Event
{
    private final BorealisServer server;
    private HomePageEntry entry;

    public BorealisHomePageEvent(BorealisServer s, HomePageEntry e)
    {
        server = s;
        entry = e;
    }

    public BorealisServer getAuroraServer()
    {
        return server;
    }

    public void add(HomePageEntry e)
    {
        entry.add(e);
    }
}