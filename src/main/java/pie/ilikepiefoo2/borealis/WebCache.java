package pie.ilikepiefoo2.borealis;

import pie.ilikepiefoo2.borealis.page.WebPage;
import java.util.HashMap;
import java.util.function.Function;

public class WebCache extends HashMap<String, WebPage> {
    public static final WebCache INSTANCE = new WebCache();
    private WebCache()
    {
        super();
    }
    public static WebPage getOrCreate(String uri, Function<? super String, ? extends WebPage> createFunction)
    {
        return INSTANCE.computeIfAbsent(uri,createFunction);
    }
}
