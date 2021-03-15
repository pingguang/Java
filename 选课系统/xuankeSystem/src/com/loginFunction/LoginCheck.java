package com.loginFunction;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class LoginCheck
{
	private Connection conn;
	private PreparedStatement statement;
	private String sql;
	private String strConn;
    private String strURL;
    private String userName;
    private String userPass;
    private ResultSet res;
    
    public LoginCheck()   //初始化时连接数据库
    {
    	this.conn=null;
    	this.statement=null;
    	this.res=null;
        this.strConn ="org.gjt.mm.mysql.Driver";    //加载驱动
        this.strURL ="jdbc:mysql://localhost:3306/xuanke";//数据库连接的地址
        this.userName = "root";
        this.userPass = "root";
        try
		{
			Class.forName(strConn);
			conn=  DriverManager.getConnection(strURL,userName,userPass);	
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
    }
  
    public boolean compare(String userShenfen,String username, String password)//匹配用户名和密码
    {
    	boolean m = false;
    	if(userShenfen=="学 生")
             sql = "select Password from student where Username=\"" + username + "\"";
    	else if(userShenfen=="教 师")
            sql = "select Password from teacher where Username=\"" + username + "\"";
    	else if(userShenfen=="管理员")
            sql = "select Password from admin where Username=\"" + username + "\"";
    	
        try {
        	statement=conn.prepareStatement(sql);
            res = statement.executeQuery();
            if (res.next()) 
            {
                String pa = res.getString(1); //得到用户的密码，并匹配看是否符合
                if (pa.equals(password)) 
                {
                    m = true;
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "密码错误！");
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "用户名不存在！");
            }
            res.close();
            conn.close();
            statement.close();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }
    
  
    public void insert(String userShenfen,String username, String password)//用户注册功能 
    {
        if (username == null || username.trim().length() <= 0)  //username.trim().length()去掉账号两边的空格后的长度
        {
            JOptionPane.showMessageDialog(null, "注册账号为空，请输入账号！");
            return;
        }
        if (password == null || password.trim().length() <= 0) 
        {
            JOptionPane.showMessageDialog(null, "注册密码为空，请输入密码！");
            return;
        }
        
        if(userShenfen=="学 生")
        	sql = "insert into student values(\"" + username + "\",\"" + password + "\",'0','0','boy','0','0','0','0')";
   	    else if(userShenfen=="教 师")
   	    	sql = "insert into teacher values(\"" + username + "\",\"" + password + "\",'0','0','boy','0','0','0')";
        
        try {
        	statement=conn.prepareStatement(sql);
            int a = statement.executeUpdate();
            if (a == 1)
            {
                JOptionPane.showMessageDialog(null, "注册成功！");
            }
            conn.close();
            statement.close();
        } catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "该用户已经存在，注册失败，请重新注册！");
            e.printStackTrace();
        }
    }
	
}
