package com.servceFunction;

import com.typeBase.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GetChoice
{
    public GetChoice()
    {
    	
    }
    public static ArrayList<Choice> getChoice(String sql)
    {
    	ArrayList<Choice> arrayList = new ArrayList<Choice>();    //用来装选课信息
    	Connection conn = null;
		Statement st = null;
	    ResultSet rs = null;
	    String path = null; 	
	    try {
	    	 Class.forName("org.gjt.mm.mysql.Driver");         //加载驱动
			 String url = "jdbc:mysql://localhost:3306/xuanke";//数据库连接的地址
			 String user = "root";
			 String password = "root";
			 conn = DriverManager.getConnection(url, user, password);
			  st=conn.createStatement();
		      rs=st.executeQuery(sql);
		      while(rs.next())
		      {
		    	 Choice c =new Choice(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)); 
		    	 arrayList.add(c);
		      }
		      conn.close();
			  st.close();
			  rs.close();
			} catch (Exception e1) 
	         {
				e1.printStackTrace();
			 } 
		return arrayList;
	}
}
