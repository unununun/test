package com.neusoft.usermanagersys;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
// 你好
public class Main {
//你好
	public static void main(String[] args) {

		System.out.println("欢迎登陆neusoft用户管理系统");
		System.out.println("====================");
		System.out.println("用户登陆--------------1");
		System.out.println("用户注册--------------2");
		System.out.println("退出程序--------------3");
		

			Scanner s = new Scanner(System.in);
			String r = s.next();
			if("1".equals(r)){
				System.out.println("用户登陆界面");
				System.out.println("================");
				System.out.println("请输入用户名:");
				String user_name = s.next();
				System.out.println("请输入用户密码:");
				//String password = Tools.EncoderByMd5(s.next());
				String password = s.next();
				UserManager uManager = new UserManager();
				User u = uManager.login(user_name, password);
				if(u==null){
					System.out.println("用户名密码错误！！！");
				}else{
					
					if(u.getRole()==0){
						
						System.out.println("登陆成功！！！");
						System.out.println("==========");
						System.out.println("欢迎登陆主窗体");
						System.out.println(u.getUsername()+"你好		您的权限是:管理员");
						System.out.println("添加用户--1");
						System.out.println("删除用户--2");
						System.out.println("修改用户--3");
						System.out.println("查询用户--4");
						System.out.println("程序退出--5");
						
						String adminMenuNum = s.next();
						if("1".equals(adminMenuNum)){
							
							User a_u = new User();
							System.out.println("请输入您要添加的用户名：");
							String unameString=s.next();
							if(Tools.isUserNameRepeat(unameString)){
								System.out.println("您输入的用户名已存在，请重新输入");
							}else{
								a_u.setUsername(unameString);
								System.out.println("请输入您要添加的密码：");
								//a_u.setPassword(Tools.EncoderByMd5(s.next()));
								a_u.setPassword(s.next());
								System.out.println("请输入您要添加的邮箱：");
								a_u.setEmail(s.next());
								System.out.println("请输入您要添加的角色(管理员-0，普通用户-1)：");
								a_u.setRole(s.nextInt());
	
								int insertRecordCount=uManager.save(a_u);
								if(insertRecordCount>0){
									System.out.println("添加成功");
								}else {
									System.out.println("添加失败");
								}
							}
							
						}else if ("2".equals(adminMenuNum)) {
							
						}else if ("3".equals(adminMenuNum)) {//修改
							
							User a_u = new User();
							System.out.println("请输入您要修改的用户ID：");
							a_u.setId(s.nextInt());
							System.out.println("请输入您要修改的用户名：");
							a_u.setUsername(s.next());
							System.out.println("请输入您要修改的密码：");
							a_u.setPassword(s.next());
							System.out.println("请输入您要修改的邮箱：");
							a_u.setEmail(s.next());
							System.out.println("请输入您要修改的角色(管理员-0，普通用户-1)：");
							a_u.setRole(s.nextInt());

							int updateRecordCount=uManager.update(a_u);
							if(updateRecordCount>0){
								System.out.println("修改成功");
							}else {
								System.out.println("修改失败");
							}
							
						}else if ("4".equals(adminMenuNum)) {
							
							System.out.println("查询全部用户-----1");
							System.out.println("根据ID查询------2");
							System.out.println("根据姓名查询(支持模糊)---3");
							System.out.println("请输入要做的操作");
							String adminQueryMenuNum = s.next();
							if("1".equals(adminQueryMenuNum)){
								System.out.println("功能即将上线！！");
							}else if ("2".equals(adminQueryMenuNum)) {
								System.out.println("功能即将上线！！");
							}else if ("3".equals(adminQueryMenuNum)) {
								System.out.println("请输入您要查询的姓名：");
								String adminQueryMenuUserName = s.next();
								//uManager.findUserByName(adminQueryMenuUserName);
								List list =uManager.getUserListByName(adminQueryMenuUserName);
								for (int i = 0; i < list.size(); i++) {
									User u2 = (User)list.get(i);
									System.out.print("编号："+u2.getId()+"\t");
									System.out.print("姓名："+u2.getUsername()+"\t");
									System.out.print("邮箱："+u2.getRole()+"\t");
									
//									HashMap hm = (HashMap)list.get(i);
//									System.out.print("编号："+hm.get("map_id")+"\t");
//									System.out.print("姓名："+hm.get("map_user_name")+"\t");
//									System.out.print("邮箱："+hm.get("map_email")+"\t");
								}
							}
							
						}else if ("5".equals(adminMenuNum)) {
							
						}
						
					}else if (u.getRole()==1) {
						
						System.out.println("登陆成功！！！");
						System.out.println("==========");
						System.out.println("欢迎登陆主窗体");
						System.out.println(u.getUsername()+"你好		您的权限是:普通用户");
						System.out.println("修改自己信息--1");
						System.out.println("查询自己信息--2");
						System.out.println("程序退出-----3");
						
					}
	
				}
			}else if("2".equals(r)){
				System.out.println("注册");
			}else if("3".equals(r)){
				System.exit(0);
			}

	}
}
