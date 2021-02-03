package pie.ilikepiefoo2.notaurora.html;

import dev.latvian.mods.aurora.page.HTTPWebPage;
import dev.latvian.mods.aurora.tag.Tag;

import java.lang.reflect.*;

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

        body.br();
        body.h3(this.subject.toGenericString());
        body.br();

        Tag constructorTable = body.table();
        Tag firstRow = constructorTable.tr();
        firstRow.th().a("Constructors","#constructors");

        Constructor[] constructors = this.subject.getDeclaredConstructors();

        for(Constructor constructor : constructors){
            addConstructor(constructorTable,constructor);
        }

        body.br();

        Tag fieldTable = body.table();
        firstRow = fieldTable.tr();
        firstRow.th().a("Fields","#fields");
        firstRow.th().text("Type");

        Field[] fields = this.subject.getDeclaredFields();

        for(Field field : fields){
            addField(fieldTable, field);
        }

        body.br();

        Tag methodTable = body.table();
        firstRow = fieldTable.tr();
        firstRow.th().a("Methods","#methods");
        firstRow.th().text("Returns");

        Method[] methods = this.subject.getDeclaredMethods();

        for(Method method : methods){
            addMethod(methodTable,method);
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

    public static Tag linkClass(Tag previous,Class<?> aclass){
        if(!aclass.isPrimitive() && !aclass.isArray()){
            previous.a(" "+aclass.getName()+" ","/kubejs_auto_docs/"+aclass.getName());
        }else{
            previous.text(" "+aclass.getSimpleName()+" ");
        }
        return previous;
    }

    public static void addField(Tag table, Field field)
    {
        Tag row = table.tr();
        linkType(row.td().text(Modifier.toString(field.getModifiers())+" "),field.getType()).text(" "+field.getName());

        linkType(row.td(),field.getType());
    }
    public static void addMethod(Tag table, Method method)
    {
        Tag row = table.tr();
        Tag methodTag = row.td().text(Modifier.toString(method.getModifiers())+" ");
        if(method.getName().contains("lambda$")){
            methodTag = methodTag.text(method.getName().substring(7,method.getName().indexOf('$',7)));
        }else {
            methodTag = methodTag.text(method.getName());
        }
        methodTag = methodTag.text("(");
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
    public static void addConstructor(Tag table, Constructor constructor)
    {
        Tag methodTag;
        if(constructor.getName().contains("lambda$")){
            methodTag = table.tr().td().text(constructor.getName().substring(7,constructor.getName().indexOf('$',7))+"(");
        }else {
            methodTag = table.tr().td().text(constructor.getName() + "(");
        }
        for(Parameter parameter : constructor.getParameters()){
            methodTag = linkType(methodTag,parameter.getType());
            methodTag = methodTag.text(parameter.getName()+",");
        }
        methodTag.text(")");
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
