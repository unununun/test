package com.neusoft.usermanagersys;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sun.misc.BASE64Encoder;

public class Tools {
	
	public static String EncoderByMd5(String str){
		String newstr = null;
		try {
			// 确定计算方法
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			// 加密后的字符串
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newstr;
	}
	
	
	//根据输入参数判断用户名是否重复
	public static boolean isUserNameRepeat(String name){
		boolean r=false;
		Connection conn = Conn.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from user_table where user_name =? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.next()){
				r=true;
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
		return r;
	}
}
