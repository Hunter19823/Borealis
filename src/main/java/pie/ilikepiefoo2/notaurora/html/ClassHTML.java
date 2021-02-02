package pie.ilikepiefoo2.notaurora.html;

import dev.latvian.mods.aurora.page.HTTPWebPage;
import dev.latvian.mods.aurora.tag.Tag;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ClassHTML extends HTTPWebPage {
    private final Class subject;
    public ClassHTML(Class subject)
    {
        this.subject = subject;
        this.addBackButton();
    }

    @Override
    public void body(Tag body)
    {
        body.img("https://kubejs.latvian.dev/logo_title.png").style("height", "7em");
        body.br();
        body.h1("").a("KubeJS Documentation", "/kubejs_auto_docs");
        Tag fieldTable = body.table();
        Tag firstRow = fieldTable.tr();


        firstRow.th().a("Fields","#fields");
        firstRow.th().text("Type");

        Field[] fields = this.subject.getDeclaredFields();

        for(Field field : fields){
            Tag row = fieldTable.tr();
            row.td().text(field.getName());

            linkType(row.td(),field.getType());
        }



        Tag methodTable = body.table();
        firstRow = fieldTable.tr();

        firstRow.th().a("Methods","#methods");
        firstRow.th().text("Returns");

        Method[] methods = this.subject.getDeclaredMethods();

        for(Method method : methods){
            Tag row = fieldTable.tr();
            Tag methodTag = row.td().text(method.getName()+"(");
            for(Parameter parameter : method.getParameters()){
                methodTag = linkType(methodTag,parameter.getType());
                methodTag = methodTag.text(parameter.getName()+",");
            }
            methodTag.text(")");
            if(!method.getReturnType().getTypeName().equals("void")){
                linkType(row.td(),method.getReturnType());
            }else{
                row.td().text("void");
            }

        }

        body.br();
    }

    public static Tag linkType(Tag previous,Class<?> aclass){
        if(!aclass.isPrimitive() && !aclass.isArray()){
            previous.a(" "+aclass.getSimpleName()+" ","/kubejs_auto_docs/"+aclass.getName());
        }else{
            previous.text(" "+aclass.getSimpleName()+" ");
        }
        return previous;
    }

    public static void main(String[] args) throws ClassNotFoundException
    {
        Class c = Class.forName("net.minecraft.client.renderer.WorldRenderer");
        Method[] methods = c.getDeclaredMethods();

        for(Method method : methods){
            System.out.println("Name: "+method.getName());
            System.out.println("Return Type Type Name: "+method.getReturnType().getTypeName());
            System.out.println("Generic String: "+method.toGenericString());
            System.out.println("String: "+method.toString());
            System.out.println("Parameter Count: "+method.getParameters().length);
        }
    }
}
