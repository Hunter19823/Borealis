package pie.ilikepiefoo2.borealis;


import io.netty.handler.codec.http.FullHttpRequest;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import pie.ilikepiefoo2.borealis.page.WebPage;

import javax.annotation.Nullable;
import java.util.function.Function;

/**
 * @author LatvianModder
 */
@Cancelable
public class BorealisPageEvent extends Event
{
    private final BorealisServer server;
    private final FullHttpRequest request;
    private final String uri;
    private final String[] splitUri;
    private WebPage page;

    public BorealisPageEvent(BorealisServer s, FullHttpRequest r, String u)
    {
        server = s;
        request = r;
        uri = u;
        splitUri = uri.split("/");
    }

    public BorealisServer getBorealisServer()
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

    public void cachePage(String uri, Function<? super String, ? extends WebPage> supplier)
    {
        returnPage(WebCache.getOrCreate(uri,supplier));
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