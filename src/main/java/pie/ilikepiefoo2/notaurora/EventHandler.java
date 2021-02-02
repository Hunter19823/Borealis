package pie.ilikepiefoo2.notaurora;


import dev.latvian.mods.aurora.page.HomePageEntry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pie.ilikepiefoo2.notaurora.html.ClassHTML;
import pie.ilikepiefoo2.notaurora.html.DocumentationHomePage;

@Mod.EventBusSubscriber
public class EventHandler {

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
                event.returnPage(new DocumentationHomePage());
            }
            else
            {
                try
                {
                    Class c = Class.forName(event.getSplitUri()[1]);
                    event.returnPage(new ClassHTML(c));
                }
                catch (Exception ex)
                {
                    //event.returnPage(new KubeJSClassErrorPage(event.getSplitUri()[1]));
                }
            }

        }
    }
}
