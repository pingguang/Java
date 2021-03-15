package com.servceFunction;

import com.typeBase.*;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;

public class CourseInsert extends JDialog implements ActionListener 
{
	private JLabel jlCname, jlChour, jlCdemand, jlCcontent,jlCpattern;
	private JTextField jtfCname, jtfChour, jtfCdemand, jtfCcontent,jtfCpattern;
	private JPanel jp1, jp2;
	private JButton submit, cancel;
	private Teacher teacher;
	
	public CourseInsert(Frame owner, String title, boolean modal,Teacher teacher) 
	{
	   super(owner, title, modal);
	   this.teacher = teacher;
	   init();
	   setBounds(500,400,300,200);
	   setVisible(true);
	}
	 
	public void init()
	{
		jlCname = new JLabel("课程名称");
		jlChour = new JLabel("课程学时");
		jlCdemand = new JLabel("课程要求");
		jlCcontent= new JLabel("课程内容");
		jlCpattern = new JLabel("考核方式");
			
		jtfCname = new JTextField(20);
		jtfChour = new JTextField(20); 
		jtfCdemand = new JTextField(20); 
		jtfCcontent = new JTextField(20);
		jtfCpattern = new JTextField(20);
		
		submit = new JButton("确认");
		submit.addActionListener(this);// 监听submit按钮
		cancel = new JButton("取消");
		cancel.addActionListener(this);
	 
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp2.add(submit);
		jp2.add(cancel);
		jp1.add(jlCname); jp1.add(jtfCname);
		jp1.add(jlChour);  jp1.add(jtfChour);
		jp1.add(jlCdemand); jp1.add(jtfCdemand);
		jp1.add(jlCcontent); jp1.add(jtfCcontent);
		jp1.add(jlCpattern); jp1.add(jtfCpattern);
		// 设置布局
	    jp1.setLayout(new GridLayout(5, 2));
		add(jp1);
		add(jp2, BorderLayout.SOUTH);
	}
		
	public void actionPerformed(ActionEvent e)
	{		
	   if (e.getSource() == submit) // 提交按钮被点击
	   {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {	
				Class.forName("org.gjt.mm.mysql.Driver");//加载驱动
				String url = "jdbc:mysql://localhost:3306/xuanke";//数据库连接的地址
				String user = "root";
				String password = "root";
				conn = (Connection) DriverManager.getConnection(url, user, password);
				String insert = "insert into course values(?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(insert);
	 
			// 获取输入框数据,并插入到数据库中
				pstmt.setString(1, jtfCname.getText());
				pstmt.setString(2, jtfChour.getText());
				pstmt.setString(3, jtfCdemand.getText());
				pstmt.setString(4, jtfCcontent.getText());
				pstmt.setString(5, jtfCpattern.getText());
				pstmt.setString(6, teacher.getTname());
				pstmt.setString(7, "0");	
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "课程发布成功！");
				dispose();// 关闭对话框
				} catch (Exception e1)
			     {
					e1.printStackTrace();
				 } finally 
			     {
					try {
						if (rs != null) 
						{
							rs.close();
							rs = null;
						}
	 
						if (pstmt != null) {
							pstmt.close();
							pstmt = null;
						}
						if (conn != null)
						{
							conn.close();
							conn = null;
						}
					} catch (Exception e2) 
					{
						e2.printStackTrace();
					}
				}
			} else if (e.getSource() == cancel) //退出发布课程界面
			{
				dispose();
			}
		}
	}