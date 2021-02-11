package pie.ilikepiefoo2.borealis.tag;

public class ScriptTag extends UnpairedTag {
    public String script;
    public ScriptTag(String script)
    {
        super("script");
        this.script = script;
    }

    @Override
    public void build(StringBuilder builder)
    {
        builder.append("<"+name+">");
        builder.append(script.replace("\n",""));
        builder.append("</"+name+">");
    }
}
