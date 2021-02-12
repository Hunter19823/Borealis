package pie.ilikepiefoo2.borealis.page;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * @author LatvianModder
 */
public interface WebPage
{

    String getContent();

    default String getContentType()
    {
        return "text/html";
    }

    default HttpResponseStatus getStatus()
    {
        return HttpResponseStatus.OK;
    }

    default PageType getPageType()
    {
        return PageType.ENABLED;
    }

}