package pie.ilikepiefoo2.borealis.page;

import com.google.gson.JsonElement;

/**
 * @author LatvianModder
 */
public abstract class JsonWebPage extends Dirty implements WebPage
{
    public abstract JsonElement getJson();

    @Override
    public String getContent()
    {
        if(isDirty())
            cache(getJson().toString(),isAlwaysDirty());
        return cachedContent;
    }

    @Override
    public String getContentType()
    {
        return "application/json";
    }
}