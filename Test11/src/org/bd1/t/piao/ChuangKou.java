package org.bd1.t.piao;

public class ChuangKou extends Thread {

	public ChuangKou(String name){
		super(name);
	}
	
	static Object o = new Object();
	
	private static Integer shuliang=40;
	
	public void run(){
		while(shuliang>0){
			synchronized (o) {
				if(shuliang>0){
					shuliang--;
					System.out.println(getName() + "fff" + shuliang + "fff");
				}else{
					System.out.println("fi");
				}
			}

		}
	}
	
}
