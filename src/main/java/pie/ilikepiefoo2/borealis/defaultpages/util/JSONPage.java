package pie.ilikepiefoo2.borealis.defaultpages.util;

import pie.ilikepiefoo2.borealis.BorealisConfigHandler;
import pie.ilikepiefoo2.borealis.page.HTTPWebPage;
import pie.ilikepiefoo2.borealis.page.PageType;
import pie.ilikepiefoo2.borealis.tag.Tag;

import static pie.ilikepiefoo2.borealis.defaultpages.minecraft.MinecraftPageHandler.addTitleIcon;
import static pie.ilikepiefoo2.borealis.defaultpages.util.JSONTreeViewPage.addTreeViewStyle;

public class JSONPage extends HTTPWebPage {

    public static final String URI = "json";

    @Override
    public void head(Tag head)
    {
        super.head(head);
        addTreeViewStyle(head.style());
    }

    @Override
    public String getTitle()
    {
        return "JSON Viewer";
    }

    @Override
    public String getDescription()
    {
        return "A small tool to view JSON files/text";
    }

    @Override
    public PageType getPageType()
    {
        return BorealisConfigHandler.COMMON.jsonUtilPage.get();
    }

    @Override
    public void body(Tag body)
    {
        addTitleIcon(body);
        body.h1("Paste the JSON in the box below or upload a json file to start.");
        body.h3("File Upload: ");
        Tag fileInput = body.unpaired("input");
        fileInput.attr("type","file");
        fileInput.attr("id","jsonFileInput");
        fileInput.attr("accept",".json,application/json");
        fileInput.attr("onchange","generateLinkFromFile()");
        body.br();
        body.h3("Text Entry: ");
        Tag textInput = body.unpaired("input");
        textInput.attr("type","text");
        textInput.attr("id","jsonTextInput");
        textInput.attr("accept","json");
        textInput.attr("onchange","generateLinkFromText()");
        body.br();
        Tag link = body.ul();
        link.id("JSONTree");
        link.addClass("myUL");

        body.script(scriptString);
    }
    public static final String scriptString = "function recursiveAddKeys(previous, current)\n" +
            "{\n" +
            "    var keys = Object.keys(current);\n" +
            "    for(let i=0;i<keys.length;i++){\n" +
            "        recursiveAdd(previous,keys[i],current[keys[i]]);\n" +
            "    }\n" +
            "}\n" +
            "function recursiveAdd(previous, key, value)\n" +
            "{\n" +
            "    var li = document.createElement('LI');\n" +
            "    previous.appendChild(li);\n" +
            "    var span = document.createElement('SPAN');\n" +
            "    li.appendChild(span);\n" +
            "    var ul;\n" +
            "    if(value === null){\n" +
            "        span = spanKey(li,key,\"null\");\n" +
            "        span.classList.add(\"null\");\n" +
            "        li.classList.add(\"nulls\");\n" +
            "    }else if(value.constructor === [].constructor){\n" +
            "        span.innerText = key;\n" +
            "        span.classList.add(\"caret\");\n" +
            "        li.classList.add(\"objects\");\n" +
            "        ul = document.createElement(\"UL\");\n" +
            "        li.appendChild(ul);\n" +
            "        ul.classList.add(\"nested\");\n" +
            "        ul.classList.add(\"object\");\n" +
            "        recursiveAddKeys(ul,value);\n" +
            "    }else if(value.constructor === ({}).constructor) {\n" +
            "        span.innerText = key;\n" +
            "        span.classList.add(\"caret\");\n" +
            "        li.classList.add(\"arrays\");\n" +
            "        ul = document.createElement(\"UL\");\n" +
            "        li.appendChild(ul);\n" +
            "        ul.classList.add(\"nested\");\n" +
            "        ul.classList.add(\"array\");\n" +
            "\n" +
            "        recursiveAddKeys(ul,value);\n" +
            "    }else if(value.constructor === \"\".constructor){\n" +
            "        span = spanKey(li,key,'\"'+value+'\"');\n" +
            "        span.classList.add(\"primitive\");\n" +
            "        li.classList.add(\"primitives\");\n" +
            "    }else {\n" +
            "        span = spanKey(li,key,value);\n" +
            "        span.classList.add(\"primitive\");\n" +
            "        li.classList.add(\"primitives\");\n" +
            "    }\n" +
            "}\n" +
            "function spanKey(previous, key, value){\n" +
            "    var span = document.createElement('SPAN');\n" +
            "    previous.appendChild(span);\n" +
            "    span.innerText = key+': '+value;\n" +
            "    return span;\n" +
            "}\n" +
            "function generateLinkFromFile(){\n" +
            "    var file = document.getElementById('jsonFileInput');\n" +
            "    if(file.files.length>0){\n" +
            "        var reader = new FileReader();\n" +
            "        reader.addEventListener('load', function() {\n" +
            "            var result = JSON.parse(reader.result);\n" +
            "            document.getElementById('jsonTextInput').value = reader.result;\n" +
            "            fillTree(result);\n" +
            "        });\n" +
            "        reader.readAsText(file.files[0]);\n" +
            "    }\n" +
            "}\n" +
            "function generateLinkFromText(){\n" +
            "    var textBox = document.getElementById('jsonTextInput');\n" +
            "    try{\n" +
            "        fillTree(JSON.parse(textBox.value));\n" +
            "        console.log('JSON Tree Filled');\n" +
            "    } catch(err){\n" +
            "        console.log('JSON Tree Failed To Load');\n" +
            "        document.getElementById('JSONTree').innerHTML = err;\n" +
            "        console.error(err);\n" +
            "    }\n" +
            "}\n" +
            "function fillTree(json){\n" +
            "    var tree = document.getElementById('JSONTree');\n" +
            "    tree.innerHTML = '';\n" +
            "    try{\n" +
            "        recursiveAddKeys(tree,json);\n" +
            "        console.log('Tree Filled');\n" +
            "    } catch(err){\n" +
            "        console.log('JSON Tree Failed To Load');\n" +
            "        document.getElementById('JSONTree').innerHTML = err;\n" +
            "        console.error(err);\n" +
            "    }\n" +
            "    addClickListeners();\n" +
            "}\n" +
            "function addClickListeners(){\n" +
            "    var toggler = document.getElementsByClassName(\"caret\");\n" +
            "    var i;\n" +
            "    for (i = 0; i < toggler.length; i++) {\n" +
            "      toggler[i].addEventListener(\"click\", function() {\n" +
            "        this.parentElement.querySelector(\".nested\").classList.toggle(\"active\");\n" +
            "        this.classList.toggle(\"caret-down\");\n" +
            "      });\n" +
            "    }\n" +
            "}";
}
