package pie.ilikepiefoo2.notaurora.html;

import dev.latvian.mods.aurora.page.HTTPWebPage;
import dev.latvian.mods.aurora.tag.Tag;
import net.minecraftforge.eventbus.api.Event;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;


import java.util.Comparator;
import java.util.Set;

import static pie.ilikepiefoo2.notaurora.html.ClassHTML.linkClass;


public class DocumentationHomePage extends HTTPWebPage {

    @Override
    public void body(Tag body)
    {
        body.img("https://kubejs.latvian.dev/logo_title.png").style("height", "7em");
        body.br();
        body.h1("").a("KubeJS Documentation", "/kubejs_auto_docs");
        Reflections reflections = new Reflections("net",new SubTypesScanner(false), new ResourcesScanner());
        Set<Class<? extends Event>> classes = reflections.getSubTypesOf(Event.class);
        body.br();
        Tag table = body.table();
        Tag firstRow = table.tr();
        firstRow.th().a("Event Classes","#events");
        classes.stream().sorted(Comparator.comparing(Class::getSimpleName)).iterator().forEachRemaining(
                eventClass -> linkClass(table.tr().td(),eventClass)
        );
        body.br();
    }
    private static DocumentationHomePage instance;
    public static DocumentationHomePage getInstance()
    {
        if(instance == null)
        {
            instance = new DocumentationHomePage();
        }
        return instance;
    }
}
