package com.longge.test;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/*
 * thanks for your food 注释示例
 * */
public class Test1 {
    @Test
    public void test1(){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        while(null != classLoader){
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
        /*
        jdk.internal.loader.ClassLoaders$AppClassLoader@77556fd
        jdk.internal.loader.ClassLoaders$PlatformClassLoader@6ee52dcd
        null
        * */
    }
    @Test
    public void test2() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader); //jdk.internal.loader.ClassLoaders$AppClassLoader@77556fd
        String resourceName = "com/longge/test/Test1.class";
        Enumeration<URL> urls = classLoader.getResources(resourceName);
        while(urls.hasMoreElements()){
            URL url = urls.nextElement();
            System.out.println(url);
        }
        //file:/Users/longyonghua/Documents/WorkspaceForIdea/HelloWorld/target/classes/com/longge/test/Test1.class
    }
}
