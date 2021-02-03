package pie.ilikepiefoo2.notaurora.html;



import pie.ilikepiefoo2.notaurora.page.HTTPWebPage;
import pie.ilikepiefoo2.notaurora.tag.Tag;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static pie.ilikepiefoo2.notaurora.html.ClassHTML.linkClass;


public class DocumentationHomePage extends HTTPWebPage {

    @Override
    public void body(Tag body)
    {
        body.img("https://kubejs.latvian.dev/logo_title.png").style("height", "7em");
        body.br();
        body.h1("").a("KubeJS Documentation", "/kubejs_auto_docs");
        body.text("This section is a WIP. Try starting ")
                .a("here","/kubejs_auto_docs/dev.latvian.kubejs.event.EventsJS")
                .text(". Due to technical issues I will need to manually add events to this page.");

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

    public static void main(String[] args)
    {
        try {
            System.out.println(getClasses("pie.ilikepiefoo2.notaurora.page").length);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
