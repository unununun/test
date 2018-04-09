package org.bd1.s;

public class FuWuYuan extends Thread{
	
	private kfc f;
	
	public FuWuYuan(kfc k) {
		this.f=k;
	}

	public void run() {
		shengChanShiWu();
	}
	
	public void shengChanShiWu() {
		
		synchronized (f) {
		while(true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String sw = deDaoShiWu();
			this.f.foods.add(sw);
			System.out.println("fwy tian jia yige "+sw+",sheng yu "+this.f.foods.size());
			if(this.f.foods.size()>kfc.Max) {

					f.notifyAll();
					
					try {
						f.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

			}
		}
		}
		
	}
	
	private String deDaoShiWu() {
		int d = (int)(Math.random()*4);
		return this.f.names[d];
	}
	
}
