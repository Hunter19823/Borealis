package pie.ilikepiefoo2.borealis.tag;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author LatvianModder
 */
public class Style extends TagBase
{
    public final Map<String, StyleSelector> selectors;

    public Style()
    {
        selectors = new LinkedHashMap<>();
    }

    public StyleSelector add(String selector)
    {
        StyleSelector s = selectors.get(selector);

        if (s == null)
        {
            s = new StyleSelector(selector);
            selectors.put(selector, s);
        }

        return s;
    }

    @Override
    public boolean isEmpty()
    {
        return selectors.isEmpty();
    }

    @Override
    public void build(StringBuilder builder)
    {
        builder.append("<style>");

        for (StyleSelector selector : selectors.values())
        {
            selector.build(builder);
        }

        builder.append("</style>");
    }
}