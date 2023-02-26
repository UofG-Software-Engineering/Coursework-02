package uofg.se.group.inject;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Description
 * @Author Chris
 * @Date 2023/2/26
 */
public class ClassUtil {

    public static Set<Class<?>> getClasses(String packageName) {
        Set<Class<?>> classes = new HashSet<>();
        String path = packageName.replace(".", "/");
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                if (resource.getProtocol().equals("file")) {
                    String filePath = URLDecoder.decode(resource.getFile(), "UTF-8");
                    findClassesByFile(packageName, filePath, classes);
                } else if (resource.getProtocol().equals("jar")) {
                    JarURLConnection jarURLConnection = (JarURLConnection) resource.openConnection();
                    findClassesByJar(packageName, jarURLConnection, classes);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return classes;

    }

    private static void findClassesByFile(String packageName, String filePath, Set<Class<?>> classes) throws ClassNotFoundException {
        File file = new File(filePath);
        File[] files = file.listFiles();
        for (File subFile : files) {
            if (subFile.isDirectory()) {
                findClassesByFile(packageName + "." + subFile.getName(), subFile.getAbsolutePath(), classes);
            } else {
                String className = subFile.getName().substring(0, subFile.getName().length() - 6);
                Class<?> clazz = Class.forName(packageName + "." + className);
                classes.add(clazz);
            }
        }
    }

    private static void findClassesByJar(String packageName, JarURLConnection jarURLConnection, Set<Class<?>> classes) throws ClassNotFoundException,
            IOException {
        JarFile jarFile = jarURLConnection.getJarFile();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String jarEntryName = jarEntry.getName();
            if (jarEntryName.endsWith(".class") && jarEntryName.startsWith(packageName.replace(".", "/"))) {
                String className = jarEntryName.substring(0, jarEntryName.length() - 6).replace("/", ".");
                Class<?> clazz = Class.forName(className);
                classes.add(clazz);
            }
        }
    }
}
