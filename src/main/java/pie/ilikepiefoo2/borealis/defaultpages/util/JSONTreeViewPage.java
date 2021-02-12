package pie.ilikepiefoo2.borealis.defaultpages.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pie.ilikepiefoo2.borealis.page.HTTPWebPage;
import pie.ilikepiefoo2.borealis.tag.Style;
import pie.ilikepiefoo2.borealis.tag.StyleSelector;
import pie.ilikepiefoo2.borealis.tag.Tag;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static pie.ilikepiefoo2.borealis.defaultpages.minecraft.MinecraftPageHandler.addTitleIcon;

public class JSONTreeViewPage extends HTTPWebPage {

    private JsonElement object;

    public JSONTreeViewPage(JsonElement object){
        this.object = object;
    }

    @Override
    public void head(Tag head)
    {
        super.head(head);
        addTreeViewStyle(head.style());
    }

    public static void addTreeViewStyle(Style style){
        StyleSelector selector;
        selector = style.add("ul");
        selector.set("list-style-type","inherit");

        selector = style.add(".myUl");
        selector.set("margin", "0");
        selector.set("padding", "0");
        selector.set("list-style-type","none");

        selector = style.add(".caret");
        selector.set("cursor","pointer");
        selector.set("-webkit-user-select","none");
        selector.set("-moz-user-select","none");
        selector.set("-ms-user-select","none");
        selector.set("user-select","none");

        selector = style.add(".caret::before");
        selector.set("content","\"+\"");
        selector.set("color","white");
        selector.set("display","inline-block");
        selector.set("margin-right","6px");

        selector = style.add(".caret-down::before");
        selector.set("content","\"-\"");

        selector = style.add(".objects");
        selector.set("list-style-image","url('http://jsonviewer.stack.hu/object.gif')");

        selector = style.add(".arrays");
        selector.set("list-style-image","url('http://jsonviewer.stack.hu/array.gif')");

        selector = style.add(".primitives");
        selector.set("list-style-image","url('http://jsonviewer.stack.hu/green.gif')");

        selector = style.add(".primitive::before");
        selector.set("content","\"◆\"");
        selector.set("color","green");
        selector.set("margin-right","6px");

        selector = style.add(".nulls");
        selector.set("list-style-image","url('http://jsonviewer.stack.hu/green.gif')");

        selector = style.add(".null::before");
        selector.set("color","red");
        selector.set("content","\"◆\"");
        selector.set("margin-right","6px");

        selector = style.add("ul");
        selector.set("margin-left","6px");

        selector = style.add(".nested");
        selector.set("display","none");

        selector = style.add(".active");
        selector.set("display","block");
    }

    @Override
    public void body(Tag body)
    {
        addTitleIcon(body);
        recursiveAdd(body.ul().addClass("myUL"),"View",this.object);
        /*
        Tag tree = body.ul();
        tree.id("myUL");
        Tag beverages = tree.li();
        beverages.span("Beverages").addClass("box");
        Tag ul = beverages.ul();
        ul.addClass("nested");
        ul.li().text("Water");
        ul.li().text("Coffee");
        Tag tea = ul.li();
        tea.span("Tea").addClass("box");
        ul = tea.ul();
        ul.addClass("nested");
        ul.li().text("Black Tea");
        ul.li().text("White Tea");
         */
        body.script("var toggler = document.getElementsByClassName(\"caret\");\n" +
                "var i;\n" +
                "\n" +
                "for (i = 0; i < toggler.length; i++) {\n" +
                "  toggler[i].addEventListener(\"click\", function() {\n" +
                "    this.parentElement.querySelector(\".nested\").classList.toggle(\"active\");\n" +
                "    this.classList.toggle(\"caret-down\");\n" +
                "  });\n" +
                "}");
    }

    public static void recursiveAdd(Tag previous, JsonObject current)
    {
        for(Map.Entry<String, JsonElement> entry : current.entrySet()){
            recursiveAdd(previous,entry.getKey(),entry.getValue());
        }
    }
    public static void recursiveAdd(Tag previous, JsonArray current)
    {
        for(int i=0; i < current.size(); i++){
            recursiveAdd(previous, i+"", current.get(i));
        }
    }
    public static void recursiveAdd(Tag previous, String key, JsonElement value)
    {
        Tag li = previous.li();
        Tag ul;
        Tag span;
        if(value.isJsonObject()){
            span = li.span(key);
            span.addClass("caret");
            li.addClass("objects");
            ul = li.ul();
            ul.addClass("nested");
            ul.addClass("object");
            recursiveAdd(ul,value.getAsJsonObject());
        }else if(value.isJsonArray()) {
            span = li.span(key);
            span.addClass("caret");
            li.addClass("arrays");
            ul = li.ul();
            ul.addClass("nested");
            ul.addClass("array");

            recursiveAdd(ul,value.getAsJsonArray());
        }else if(value.isJsonPrimitive()){
            span = spanKey(li,key,String.valueOf(value.getAsJsonPrimitive()));
            span.addClass("primitive");
            li.addClass("primitives");
        }else{
            span = spanKey(li,key,"null");
            span.addClass("null");
            li.addClass("nulls");
        }
    }
    private static Tag spanKey(Tag previous, String key, String value){
        return previous.span(String.format("%s: %s",key,value));
    }

    public static void main(String[] args)
    {
        JsonObject obj = new JsonObject();
        obj.addProperty("string","Test");
        obj.addProperty("number",9);
        obj.addProperty("double",9.9);
        JsonArray array = new JsonArray();
        for(int i=0; i<30; i+=3){
            array.add(i);
        }
        obj.add("array",array);
        JsonObject object = new JsonObject();
        object.addProperty("inner string","innerString");
        object.add("inner array",array);
        obj.add("object",object);
        obj.add("null",null);
        System.out.println(new JSONTreeViewPage(obj).getContent());
        try {
            System.out.println(URLEncoder.encode(obj.toString(), StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

