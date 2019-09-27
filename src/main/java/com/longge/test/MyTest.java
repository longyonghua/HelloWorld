package com.longge.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyTest extends ClassLoader{
	private String classLoaderName;
	private final String fileExtension = ".class";

	public MyTest(String classLoaderName){
		super();	//将系统类加载器作为该类加载器的父加载器
		this.classLoaderName = classLoaderName;
	}
	public MyTest(ClassLoader parent,String classLoaderName){
		super(parent);	//显式指定该类加载器的父加载器
		this.classLoaderName = classLoaderName;
	}

	@Override
	public String toString(){
		return "["+this.classLoaderName+"]";
	}

	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException{
		byte[] data = this.loadClassData(className);
		return this.defineClass(className,data,0,data.length);
	}

	private byte[] loadClassData(String name){
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream baos = null;
		try{
			this.classLoaderName = this.classLoaderName.replace(".","/");
			is = new FileInputStream(new File(name + this.fileExtension));
			baos = new ByteArrayOutputStream();
			int ch = 0;
			while((ch = is.read())!=-1){
				baos.write(ch);
			}
			data = baos.toByteArray();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				is.close();
				baos.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return data;
	}

	public static void main(String[] args) throws Exception{
		MyTest loader1 = new MyTest("loader1");
		Class clazz = loader1.loadClass("com.longge.test.Test1");
		Thread.sleep(2000);
//		Object obj = clazz.newInstance(); //过时
        Object obj = clazz.getDeclaredConstructor().newInstance();
		System.out.println(obj); //com.longge.test.Test1@7c30a502
	}

}