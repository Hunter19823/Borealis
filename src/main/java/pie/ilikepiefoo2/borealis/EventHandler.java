package pie.ilikepiefoo2.borealis;



import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pie.ilikepiefoo2.borealis.html.ClassHTML;
import pie.ilikepiefoo2.borealis.html.DocumentationHomePage;
import pie.ilikepiefoo2.borealis.page.HomePageEntry;
import pie.ilikepiefoo2.borealis.page.WebPage;

import java.util.HashMap;
import java.util.Map;


@Mod.EventBusSubscriber
public class EventHandler {
    private static final Map<Class, WebPage> knownPages = new HashMap<Class,WebPage>();

    @SubscribeEvent
    public static void homePageEvent(BorealisHomePageEvent event)
    {
        event.add(new HomePageEntry("KubeJS Documentaion","kubejs_auto_docs","https://kubejs.latvian.dev/logo_title.png"));
    }

    @SubscribeEvent
    public static void onPageEvent(BorealisPageEvent event)
    {
        if(event.getSplitUri()[0].equals("kubejs_auto_docs")) {
            if (event.getSplitUri().length == 1)
            {
                event.returnPage(DocumentationHomePage.getInstance());
            }
            else
            {
                try
                {
                    Class c = Class.forName(event.getSplitUri()[1]);
                    if(knownPages.containsKey(c)){
                        event.returnPage(knownPages.get(c));
                    }else {
                        WebPage page = new ClassHTML(c);
                        knownPages.put(c,page);
                        event.returnPage(page);
                    }
                }
                catch (Exception ex)
                {
                    //event.returnPage(new KubeJSClassErrorPage(event.getSplitUri()[1]));
                }
            }

        }
    }
}
