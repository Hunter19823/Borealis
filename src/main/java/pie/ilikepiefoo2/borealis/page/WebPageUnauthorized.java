package pie.ilikepiefoo2.borealis.page;

import io.netty.handler.codec.http.HttpResponseStatus;
import pie.ilikepiefoo2.borealis.tag.Tag;

/**
 * @author LatvianModder
 */
public class WebPageUnauthorized extends HTTPWebPage
{
    private final String uri;

    public WebPageUnauthorized(String u)
    {
        uri = u;
    }

    @Override
    public String getDescription()
    {
        return "Unauthorized";
    }

    @Override
    public void body(Tag body)
    {
        Tag text = body.h1("");
        text.text("Error! You are not authorized to view ");
        text.span(uri, "error");
    }

    @Override
    public HttpResponseStatus getStatus()
    {
        return HttpResponseStatus.UNAUTHORIZED;
    }
}