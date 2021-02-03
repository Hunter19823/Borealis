package pie.ilikepiefoo2.notaurora.tag;

import pie.ilikepiefoo2.notaurora.page.WebPage;

/**
 * @author LatvianModder
 */
public abstract class TagBase implements WebPage
{
    public PairedTag parent;

    public abstract boolean isEmpty();

    public abstract void build(StringBuilder builder);

    public String getAttribute(String key)
    {
        return "";
    }

    @Override
    public String getContent()
    {
        StringBuilder builder = new StringBuilder("<!DOCTYPE http>");
        build(builder);
        return builder.toString();
    }
}