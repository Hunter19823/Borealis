package pie.ilikepiefoo2.notaurora;


import net.minecraftforge.eventbus.api.Event;
import pie.ilikepiefoo2.notaurora.page.HomePageEntry;

/**
 * @author LatvianModder
 */
public class AuroraHomePageEvent extends Event
{
    private final NotAuroraServer server;
    private HomePageEntry entry;

    public AuroraHomePageEvent(NotAuroraServer s, HomePageEntry e)
    {
        server = s;
        entry = e;
    }

    public NotAuroraServer getAuroraServer()
    {
        return server;
    }

    public void add(HomePageEntry e)
    {
        entry.add(e);
    }
}