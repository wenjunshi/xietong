
package com.xietong.automation.fm.zrf.test.fanxing;

import java.util.Collection;



public abstract class father {
	
	public abstract void say();//穿入参数无依赖关系，使用通配符
	
	
    static <T> void form(T[] a,Collection<T> c){}//第一个形参a，依赖形参b 使用泛型方法
		
	public static void start(){
		System.out.println("Start:[Hello World!]");
	}
	
	public static void method(String pack){
		start();
		try {
			try {
				father a= (father) Class.forName(pack).newInstance();
				a.say();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public father(){}
   
}
