package com.neusoft.usermanagersys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//llcccc
//dd
//lc111
public class UserManager {
	
	public User login(String user_name,String password){
		User user = null;
		Connection conn = Conn.getConn();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from user_table where user_name='"+user_name+"' and password='"+password+"'";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("user_name"));
				user.setRole(rs.getInt("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return user;
	}
	
	//向表里添加记录
	public int save(User u){
		int r = 0;
		Connection conn = Conn.getConn();
		PreparedStatement pstmt=null;
		try {
			String sql="insert into user_table(id,user_name,password,email,role) values(user_table_id_seq.nextval,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getEmail());
			pstmt.setInt(4, u.getRole());
			r=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return r;
	
	}
	
	public void delete(){
		
	}
	
	//修改
	public int update(User u){
		int r = 0;
		Connection conn = Conn.getConn();
		PreparedStatement pstmt=null;
		try {
			String sql="update user_table set user_name=?,password=?,email=?,role=? where id=?";
			//pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getEmail());
			pstmt.setInt(4, u.getRole());
			pstmt.setInt(5, u.getId());
			//If ps is a DebuggableStatement, you see the statement,
			//otherwise, an object identifier is displayed
			//System.out.println(" debuggable statement= " + ps.toString());
			r=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return r;

	}
	
	public void findUserById(){
		
	}
	
	//直接在这个类里输出控制台
	public void findUserByName(String name){
		Connection conn = Conn.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from user_table where user_name like ? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs=pstmt.executeQuery();
			while(rs.next()){
				System.out.print("编号："+rs.getString("id")+"\t");
				System.out.print("姓名："+rs.getString("user_name")+"\t");
				System.out.print("邮箱："+rs.getString("email")+"\t");
				if("0".equals(rs.getString("role"))){
					System.out.println("角色：管理员");
				}else{
					System.out.println("角色：普通用户");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	//以集合的形式返回主函数里，在主函数里输出控制台
	public List getUserListByName(String name){
		List list = new ArrayList();
		Connection conn = Conn.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from user_table where user_name like ? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs=pstmt.executeQuery();
			while(rs.next()){
				
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("user_name"));
				u.setEmail(rs.getString("email"));
				u.setRole(rs.getInt("role"));
				list.add(u);
				
//				HashMap hm = new HashMap();
//				hm.put("map_id", rs.getInt("id"));
//				hm.put("map_user_name", rs.getInt("user_name"));
//				hm.put("map_email", rs.getInt("email"));
//				hm.put("map_role", rs.getInt("role"));
//				list.add(hm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}

	
	
	public void findAll(){
		
	}
	
	public void exit(){
		
	}
}
