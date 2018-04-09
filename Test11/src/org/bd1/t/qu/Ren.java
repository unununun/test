package org.bd1.t.qu;

public class Ren extends Thread {
	
	public Ren(String name){
		super(name);
	}
	
	static Object o = new Object();
	
	public void quqian(){
		
		while(YinHang.qian>0){
			synchronized (o) {
				if(YinHang.qian>0){
					YinHang.qian=YinHang.qian-100;
					System.out.println(getName() + "dd" + 100 + "dd"+YinHang.qian);
				}else{
					System.out.println("fi");
				}
			}

		}
	}
	
	public void run(){
		quqian();
	}
	
}
