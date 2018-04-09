package org.bd1.s;

public class KeHu extends Thread{
	
	private kfc f;
	
	public KeHu(kfc k,String name) {
		super(name);
		this.f=k;
	}
	
	public void run() {
		xiaoFeiShiWu();
	}

	public void xiaoFeiShiWu() {
		synchronized (f) {
		while(true) {

			
			if(this.f.foods.size()==0) {
				try {
					f.notifyAll();
					f.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				this.f.foods.remove(0);
				System.out.println(getName()+":吃了一个,haisheng:"+this.f.foods.size());
			}
		}
		}
	}
	
	
}
