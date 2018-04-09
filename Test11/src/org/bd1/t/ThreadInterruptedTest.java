package org.bd1.t;
/**
 * 
 * 一个线程的生命周期分为：初始、就绪、运行、阻塞以及结束。当然，其中也可以有四种状态，初始、就绪、运行以及结束。
 * 
 * 一般而言，可能有三种原因引起阻塞：等待阻塞、同步阻塞以及其他阻塞（睡眠、jion或者IO阻塞）；对于Java而言，等待阻塞是调用wait方法产生的，同步阻塞则是由同步块（synchronized）产生的，睡眠阻塞是由sleep产生的，jion阻塞是由jion方法产生的。
 * 
 * 言归正传，要中断一个Java线程，可调用线程类（Thread）对象的实例方法：interrupte()；然而interrupte()方法并不会立即执行中断操作；具体而言，这个方法只会给线程设置一个为true的中断标志（中断标志只是一个布尔类型的变量），而设置之后，则根据线程当前的状态进行不同的后续操作。如果，线程的当前状态处于非阻塞状态，那么仅仅是线程的中断标志被修改为true而已；如果线程的当前状态处于阻塞状态，那么在将中断标志设置为true后，还会有如下三种情况之一的操作：
 * 
 * 如果是wait、sleep以及jion三个方法引起的阻塞，那么会将线程的中断标志重新设置为false，并抛出一个InterruptedException；
 * 如果是java.nio.channels.InterruptibleChannel进行的io操作引起的阻塞，则会对线程抛出一个ClosedByInterruptedException；（待验证）
 * 如果是轮询（java.nio.channels.Selectors）引起的线程阻塞，则立即返回，不会抛出异常。（待验证）
 * 如果在中断时，线程正处于非阻塞状态，则将中断标志修改为true,而在此基础上，一旦进入阻塞状态，则按照阻塞状态的情况来进行处理；例如，一个线程在运行状态中，其中断标志被设置为true,则此后，一旦线程调用了wait、jion、sleep方法中的一种，立马抛出一个InterruptedException，且中断标志被清除，重新设置为false。
 * 通过上面的分析，我们可以总结，调用线程类的interrupted方法，其本质只是设置该线程的中断标志，将中断标志设置为true，并根据线程状态决定是否抛出异常。因此，通过interrupted方法真正实现线程的中断原理是：开发人员根据中断标志的具体值，来决定如何退出线程。
 *
 * 1，线程正常执行完毕，正常结束。
 * 
 * 也就是让run方法执行完毕，该线程就会正常结束。
 * 
 * 2，监视某些条件，结束线程的不间断运行。
 */
public class ThreadInterruptedTest{
	
	public static void main(String[] args) {
		ThreadInterrupted ti = new ThreadInterrupted();
		new Thread(ti).start();
		System.out.println("ddd");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ti.setFinished(true);
	}
	
}
