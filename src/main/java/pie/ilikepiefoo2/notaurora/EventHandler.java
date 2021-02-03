package pie.ilikepiefoo2.notaurora;



import dev.latvian.kubejs.script.BindingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pie.ilikepiefoo2.notaurora.html.ClassHTML;
import pie.ilikepiefoo2.notaurora.html.DocumentationHomePage;
import pie.ilikepiefoo2.notaurora.page.HomePageEntry;
import pie.ilikepiefoo2.notaurora.page.WebPage;

import java.util.HashMap;
import java.util.Map;


@Mod.EventBusSubscriber
public class EventHandler {
    private static final Map<Class, WebPage> knownPages = new HashMap<Class,WebPage>();

    @SubscribeEvent
    public static void homePageEvent(AuroraHomePageEvent event)
    {
        event.add(new HomePageEntry("KubeJS Documentaion","kubejs_auto_docs","https://kubejs.latvian.dev/logo_title.png"));
    }

    @SubscribeEvent
    public static void onPageEvent(AuroraPageEvent event)
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
