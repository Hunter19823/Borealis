package pie.ilikepiefoo2.notaurora;

import dev.latvian.mods.aurora.page.WebPage;
import io.netty.handler.codec.http.FullHttpRequest;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;

/**
 * @author LatvianModder
 */
@Cancelable
public class AuroraPageEvent extends Event
{
    private final NotAuroraServer server;
    private final FullHttpRequest request;
    private final String uri;
    private final String[] splitUri;
    private WebPage page;

    public AuroraPageEvent(NotAuroraServer s, FullHttpRequest r, String u)
    {
        server = s;
        request = r;
        uri = u;
        splitUri = uri.split("/");
    }

    public NotAuroraServer getAuroraServer()
    {
        return server;
    }

    public FullHttpRequest getRequest()
    {
        return request;
    }

    public String getUri()
    {
        return uri;
    }

    public String[] getSplitUri()
    {
        return splitUri;
    }

    @Nullable
    public WebPage getPage()
    {
        return page;
    }

    public void setPage(WebPage p)
    {
        page = p;
    }

    public void returnPage(WebPage p)
    {
        setPage(p);
        setCanceled(true);
    }

    public boolean checkPath(String... path)
    {
        if (path.length > splitUri.length)
        {
            return false;
        }

        for (int i = 0; i < path.length; i++)
        {
            if (!splitUri[i].equals(path[i]) && !path[i].equals("*"))
            {
                return false;
            }
        }

        return true;
    }
}