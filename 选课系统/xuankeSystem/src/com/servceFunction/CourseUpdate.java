package com.servceFunction;


import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class CourseUpdate
{
    public static void update(String cname) {
    	Connection conn = null;
		Statement stmt = null;
		try {	
			    Class.forName("org.gjt.mm.mysql.Driver");//加载驱动
				String url = "jdbc:mysql://localhost:3306/xuanke";//数据库连接的地址
				String user = "root";
				String password = "root";
				conn = (Connection) DriverManager.getConnection(url, user, password);
				String sql = "update course set Cexcamine='1' where Cname=\"" + cname + "\"";
				conn.setAutoCommit(false);
				stmt =(Statement) conn.createStatement();   //用来执行Sql语句的一个对象（内含很多执行语句）
				stmt.executeUpdate(sql);
				conn.commit();
			 } catch (Exception e1) 
		      {
  				e1.printStackTrace();
              }finally 
		      {
            	  try {
						if (stmt != null) 
						{
							stmt.close();
							stmt = null;
						}
						if (conn != null) 
						{
							conn.close();
							conn = null;
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		      }
    }
}
