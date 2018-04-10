package org.bd1.t.kfc;

public class M {

	public static void main(String[] args) {
		kfc k = new kfc();
		KeHu kh1 = new KeHu(k,"呵呵");

		FuWuYuan fwy1 = new FuWuYuan(k);
		
		fwy1.start();
		kh1.start();
		
		
		

	}

}
