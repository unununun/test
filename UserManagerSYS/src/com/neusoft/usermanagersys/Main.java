package com.neusoft.usermanagersys;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
// ���
public class Main {
//���
	public static void main(String[] args) {

		System.out.println("��ӭ��½neusoft�û�����ϵͳ");
		System.out.println("====================");
		System.out.println("�û���½--------------1");
		System.out.println("�û�ע��--------------2");
		System.out.println("�˳�����--------------3");
		

			Scanner s = new Scanner(System.in);
			String r = s.next();
			if("1".equals(r)){
				System.out.println("�û���½����");
				System.out.println("================");
				System.out.println("�������û���:");
				String user_name = s.next();
				System.out.println("�������û�����:");
				//String password = Tools.EncoderByMd5(s.next());
				String password = s.next();
				UserManager uManager = new UserManager();
				User u = uManager.login(user_name, password);
				if(u==null){
					System.out.println("�û���������󣡣���");
				}else{
					
					if(u.getRole()==0){
						
						System.out.println("��½�ɹ�������");
						System.out.println("==========");
						System.out.println("��ӭ��½������");
						System.out.println(u.getUsername()+"���		����Ȩ����:����Ա");
						System.out.println("����û�--1");
						System.out.println("ɾ���û�--2");
						System.out.println("�޸��û�--3");
						System.out.println("��ѯ�û�--4");
						System.out.println("�����˳�--5");
						
						String adminMenuNum = s.next();
						if("1".equals(adminMenuNum)){
							
							User a_u = new User();
							System.out.println("��������Ҫ��ӵ��û�����");
							String unameString=s.next();
							if(Tools.isUserNameRepeat(unameString)){
								System.out.println("��������û����Ѵ��ڣ�����������");
							}else{
								a_u.setUsername(unameString);
								System.out.println("��������Ҫ��ӵ����룺");
								//a_u.setPassword(Tools.EncoderByMd5(s.next()));
								a_u.setPassword(s.next());
								System.out.println("��������Ҫ��ӵ����䣺");
								a_u.setEmail(s.next());
								System.out.println("��������Ҫ��ӵĽ�ɫ(����Ա-0����ͨ�û�-1)��");
								a_u.setRole(s.nextInt());
	
								int insertRecordCount=uManager.save(a_u);
								if(insertRecordCount>0){
									System.out.println("��ӳɹ�");
								}else {
									System.out.println("���ʧ��");
								}
							}
							
						}else if ("2".equals(adminMenuNum)) {
							
						}else if ("3".equals(adminMenuNum)) {//�޸�
							
							User a_u = new User();
							System.out.println("��������Ҫ�޸ĵ��û�ID��");
							a_u.setId(s.nextInt());
							System.out.println("��������Ҫ�޸ĵ��û�����");
							a_u.setUsername(s.next());
							System.out.println("��������Ҫ�޸ĵ����룺");
							a_u.setPassword(s.next());
							System.out.println("��������Ҫ�޸ĵ����䣺");
							a_u.setEmail(s.next());
							System.out.println("��������Ҫ�޸ĵĽ�ɫ(����Ա-0����ͨ�û�-1)��");
							a_u.setRole(s.nextInt());

							int updateRecordCount=uManager.update(a_u);
							if(updateRecordCount>0){
								System.out.println("�޸ĳɹ�");
							}else {
								System.out.println("�޸�ʧ��");
							}
							
						}else if ("4".equals(adminMenuNum)) {
							
							System.out.println("��ѯȫ���û�-----1");
							System.out.println("����ID��ѯ------2");
							System.out.println("����������ѯ(֧��ģ��)---3");
							System.out.println("������Ҫ���Ĳ���");
							String adminQueryMenuNum = s.next();
							if("1".equals(adminQueryMenuNum)){
								System.out.println("���ܼ������ߣ���");
							}else if ("2".equals(adminQueryMenuNum)) {
								System.out.println("���ܼ������ߣ���");
							}else if ("3".equals(adminQueryMenuNum)) {
								System.out.println("��������Ҫ��ѯ��������");
								String adminQueryMenuUserName = s.next();
								//uManager.findUserByName(adminQueryMenuUserName);
								List list =uManager.getUserListByName(adminQueryMenuUserName);
								for (int i = 0; i < list.size(); i++) {
									User u2 = (User)list.get(i);
									System.out.print("��ţ�"+u2.getId()+"\t");
									System.out.print("������"+u2.getUsername()+"\t");
									System.out.print("���䣺"+u2.getRole()+"\t");
									
//									HashMap hm = (HashMap)list.get(i);
//									System.out.print("��ţ�"+hm.get("map_id")+"\t");
//									System.out.print("������"+hm.get("map_user_name")+"\t");
//									System.out.print("���䣺"+hm.get("map_email")+"\t");
								}
							}
							
						}else if ("5".equals(adminMenuNum)) {
							
						}
						
					}else if (u.getRole()==1) {
						
						System.out.println("��½�ɹ�������");
						System.out.println("==========");
						System.out.println("��ӭ��½������");
						System.out.println(u.getUsername()+"���		����Ȩ����:��ͨ�û�");
						System.out.println("�޸��Լ���Ϣ--1");
						System.out.println("��ѯ�Լ���Ϣ--2");
						System.out.println("�����˳�-----3");
						
					}
	
				}
			}else if("2".equals(r)){
				System.out.println("ע��");
			}else if("3".equals(r)){
				System.exit(0);
			}

	}
}
