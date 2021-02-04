package pie.ilikepiefoo2.borealis.page;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pie.ilikepiefoo2.borealis.tag.Tag;

/**
 * @author LatvianModder
 */
public class WebPageNotFound extends HTTPWebPage
{
    public static final Logger LOGGER = LogManager.getLogger("Borealis");
    private final String uri;

    public WebPageNotFound(String u)
    {
        uri = u;
        LOGGER.error("Page not found: "+u);
    }

    @Override
    public String getDescription()
    {
        return "Page not found";
    }

    @Override
    public void body(Tag body)
    {
        Tag text = body.h1("");
        text.text("Error! Page ");
        text.span(uri, "error");
        text.text(" not found!");
    }

    @Override
    public HttpResponseStatus getStatus()
    {
        return HttpResponseStatus.NOT_FOUND;
    }
}