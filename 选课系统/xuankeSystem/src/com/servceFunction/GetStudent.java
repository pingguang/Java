package com.servceFunction;

import com.typeBase.*;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class GetStudent
{
    public GetStudent()
    {	
    }
    public Student getStudent(String username)
    {
    	Student student = null;
    	Statement stmt = null;
    	Connection conn = null;
    	ResultSet rs = null;
    	String sql= "select * from student where Username=\"" + username + "\"";	
		try {
			Class.forName("org.gjt.mm.mysql.Driver");         //加载驱动
			String url = "jdbc:mysql://localhost:3306/xuanke";//数据库连接的地址
			String user = "root";
			String password = "root";
			conn = (Connection) DriverManager.getConnection(url, user, password);
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next())//获取查询数据 ,只有一条数据，因为用户名唯一
		    {
			    student = new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
			}
			
		} catch(Exception e1) {
			e1.printStackTrace();
		}finally 
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
		return student; 
    }
	
}
