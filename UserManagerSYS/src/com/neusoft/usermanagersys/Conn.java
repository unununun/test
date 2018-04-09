package com.neusoft.usermanagersys;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {

	public static Connection getConn(){
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:neuedu";
			String user = "ums";
			String password = "ums";
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
