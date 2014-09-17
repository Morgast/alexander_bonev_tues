package org.elsysbg.ip.java;

public class Fundamentals {

	private static int a;
	public static void main(String[] args) {
		
		a=0;
		ClassToDefine objSample1 = new ClassToDefine(); //defining the first object
		ClassToDefine objSample2 = objSample1; // obj2 is supposed to be the same as obj1
		ClassToDefine objSample3 = new ClassToDefine(); // obj3 must be the same type as obj1 but is completely different object
		
		for(int i=1; i<11;i++){
			if(i%2==0){
				System.out.println(a+i);
			}
		}
		if(objSample1.equals(objSample2)) {
			
			System.out.println("good"); // voala! it works
		} else {
			System.out.println("bad");
		}
		
if(objSample1.equals(objSample3)) {
			
			System.out.println("better");
		} else {
			System.out.println("worse"); //awesome!
		}
	}

}
