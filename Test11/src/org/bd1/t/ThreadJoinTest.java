package org.bd1.t;

public class ThreadJoinTest {

	public static void main(String[] args) {
		ThreadJoin t1 = new ThreadJoin("1");
		ThreadJoin t2 = new ThreadJoin("2");
		t1.start();
		/**
		 * join����˼��ʹ�÷�����ǰ�̵߳�ִ�У������ض�Ӧ���̣߳���������������˼���ǣ�
		 * ������main�߳��е���t1�̵߳�join��������main�̷߳���cpu����Ȩ��������t1�̼߳���ִ��ֱ���߳�t1ִ�����
		 * ���Խ����t1�߳�ִ����󣬲ŵ����߳�ִ�У��൱����main�߳���ͬ��t1�̣߳�t1ִ�����ˣ�main�̲߳���ִ�еĻ���
		 */
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}

}
