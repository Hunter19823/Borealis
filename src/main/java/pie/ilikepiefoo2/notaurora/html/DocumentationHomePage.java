package pie.ilikepiefoo2.notaurora.html;

import dev.latvian.mods.aurora.page.HTTPWebPage;
import dev.latvian.mods.aurora.tag.Tag;

public class DocumentationHomePage extends HTTPWebPage {

    @Override
    public void body(Tag body)
    {
        body.img("https://kubejs.latvian.dev/logo_title.png").style("height", "7em");
        body.br();
        body.h1("").a("KubeJS Documentation", "/kubejs_auto_docs");

    }
}
