package pie.ilikepiefoo2.borealis.page;

import pie.ilikepiefoo2.borealis.tag.PairedTag;
import pie.ilikepiefoo2.borealis.tag.Tag;

import static pie.ilikepiefoo2.borealis.Borealis.MOD_NAME;

/**
 * @author LatvianModder
 */
public abstract class HTTPWebPage extends Dirty implements WebPage
{
    @Override
    public String getContent()
    {
        if(isDirty()) {
            Tag http = new PairedTag("html");
            Tag head = http.paired("head");
            head.unpaired("meta").attr("charset", "UTF-8");
            head(head);
            Tag body = http.paired("body");
            body(body);

            if (addBackButton()) {
                body.h3("").a("< Back to "+MOD_NAME+" index page", "/");
            }
            cache(http.getContent(), isAlwaysDirty());
        }
        return cachedContent;
    }

    public String getTitle()
    {
        return MOD_NAME;
    }

    public String getDescription()
    {
        return "";
    }

    public String getStylesheet()
    {
        return "https://aurora.latvian.dev/style.css";
    }

    public String getIcon()
    {
        return "https://aurora.latvian.dev/logo.gif";
    }

    public boolean addBackButton()
    {
        return true;
    }

    public void head(Tag head)
    {
        String d = getDescription();

        if (!d.isEmpty())
        {
            head.paired("title", getTitle() + " - " + d);
            head.meta("description", d);
        }
        else
        {
            head.paired("title", getTitle());
        }

        head.unpaired("link").attr("rel", "icon").attr("href", getIcon());

        String s = getStylesheet();

        if (!s.isEmpty())
        {
            head.unpaired("link").attr("rel", "stylesheet").attr("type", "text/css").attr("href", s);
        }

        head.meta("viewport", "width=device-width, initial-scale=1.0");
    }

    public abstract void body(Tag body);
}