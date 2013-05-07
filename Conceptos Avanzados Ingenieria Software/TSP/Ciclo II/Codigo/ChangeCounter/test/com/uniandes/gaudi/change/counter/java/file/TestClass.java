package com.uniandes.gaudi.change.counter.java.file;

/**
 * This is a test class for the parse file
 * 
 * @class TestClass.java
 * @author Felipe
 * @Date 8/04/2013
 * @since 1.0
 */
public class TestClass {

	public TestClass() {
		System.out.println("this is a constructor");
	}
	
	public void test1() {
		System.out.println("this is test1");
	}
	
	public void test2(String param) {
		System.out.println("this is test 2 " + param);
	}
	
	public <T>void test3(Class<T> clazz) {
		System.out.println("This is test 3 "+ clazz);
	}
}
