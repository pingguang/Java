package com.servceFunction;

import com.typeBase.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class GetTeacher
{
    public GetTeacher()
    {	
    }
    public Teacher getTeacher(String username)
    {
    	Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Teacher teacher = null;
		String sql = "select * from teacher where Username=\"" + username + "\"";
		try {
			Class.forName("org.gjt.mm.mysql.Driver");         //加载驱动
			String url = "jdbc:mysql://localhost:3306/xuanke";//数据库连接的地址
			String user = "root";
			String password = "root";
			
			conn = (Connection) DriverManager.getConnection(url, user, password);
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			if(rs.next())//获取查询数据 ,数据唯一，因为用户名为关键字
			{
			   teacher = new Teacher(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
			}
			
		} catch(Exception e1) 
		{
			e1.printStackTrace();
		} finally 
		{
			
			try
			{
				conn.close();
			    stmt.close();
				rs.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return teacher;
    }
	
}
