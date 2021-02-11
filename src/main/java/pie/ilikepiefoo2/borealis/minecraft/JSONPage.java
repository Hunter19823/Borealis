package pie.ilikepiefoo2.borealis.minecraft;

import pie.ilikepiefoo2.borealis.page.HTTPWebPage;
import pie.ilikepiefoo2.borealis.tag.Tag;

public class JSONPage extends HTTPWebPage {
    @Override
    public void body(Tag body)
    {
        body.h1("Place the JSON in the box below and hit submit.");
        body.text("box go here");
        body.br();
        body.text("submit go here");
    }
}
