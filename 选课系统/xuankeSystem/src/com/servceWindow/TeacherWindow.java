package com.servceWindow;

import com.typeBase.*;
import com.servceFunction.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import jxl.write.WriteException;

public class TeacherWindow extends JFrame implements ActionListener
{ 
	private JPanel jp1,jp2;//定义控件
	private JLabel jl1,jl2;
	private JButton jb1,jb2,jb3,jb4,jb5;
	private JTable jt;	//表格
	private JScrollPane jsp;
	private JTextField jtf;
	private ChoiceTable sm;
	private String user;
	private String sql;
	private Teacher teacher;
	private GetTeacher gt;
	public TeacherWindow(String username) 
	{
		this.user = username;
		this.gt= new GetTeacher();   
		this.teacher = gt.getTeacher(user);//获得当前用户的全部信息
		init();
		super.setTitle("欢迎进入教师端");
		this.setBounds(100, 200,800, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 
	private void init() 
	{
		jp1 = new JPanel();
		jtf = new JTextField(15);
		jb1 = new JButton("选课查询");
		jb1.addActionListener(this);       //监听jb1按钮
		jl1 = new JLabel("请输入课程名：");	//按课程名查询
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		jb2 = new JButton("修改个人信息");
		jb2.addActionListener(this);//插入监听jb2按钮代码
		jb5 = new JButton("发布课程");
		jb5.addActionListener(this);//插入监听jb5按钮代码
		jb3 = new JButton("导出");
		jb3.addActionListener(this);//插入监听jb3按钮代码
		jb4 = new JButton("退出登录");
		jb4.addActionListener(this);//插入监听jb4按钮代码
		jp2 = new JPanel();
		jp2.add(jb2);
		jp2.add(jb5);
		jp2.add(jb3);
		jp2.add(jb4);
        sm = new ChoiceTable();
		jt = new JTable(sm);
		jsp = new JScrollPane(jt);
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		toFill_the_information();
	}
	
	public void toFill_the_information()
	{
		if(teacher.getTname().equals("0")&&teacher.getTcontact().equals("0"))
		{
			JOptionPane.showMessageDialog(null, "请您完善您的信息！");
		}
	}

	public void actionPerformed(ActionEvent e)
	{		
		if (e.getSource() == jb1) //课程查询
		{
			String cname = jtf.getText().trim();   //获得输入框中的课程名
			if (cname.equals("")) {
				sql = "select * from choice";   //如果没有输入就把所有的信息都输出
			} else {
				sql = "select * from choice where cname='" + cname + "'";   //按照课程名称查询
			}	
			sm = new ChoiceTable(sql);   //课程查询，把课程信息显示到表格当中
			jt.setModel(sm);  //刷新教师端的表格
		} 
		else if (e.getSource() == jb2) //修改个人信息
		{
			TeacherUpdate sa = new TeacherUpdate(this,"修改个人信息",true,teacher);  //进入修改界面，临时窗口
			this.teacher = gt.getTeacher(user);//获得当前用户的全部信息
		} 
		else if (e.getSource() == jb5)//发布课程 
		{
			CourseInsert ci = new CourseInsert(this,"添加课程界面",true,teacher);    //进入发布课程界面，临时窗口
		}
		else if (e.getSource() == jb3) //选课学生信息导出
		{
			ArrayList<Choice> arrayList = null;    //用来装选课信息
			if (jtf.getText().trim().equals(""))   //获得输入框中的课程名，若为空，则输出提示语句
			{
				JOptionPane.showMessageDialog(null, "请您输入课程名称后再导出！");
			}
			else
			{
				String sql = "select * from choice where cname='" + jtf.getText() + "'";   //按照课程名称查询   	
			    arrayList = GetChoice.getChoice(sql);
			    
			    ArrayList<String> list = new ArrayList<String>();
		        list.add("课程名称");
		        list.add("学生姓名");
		        list.add("学生专业");
		        list.add("学生班级");
		        try
				{
		        	ToExcel.out(arrayList,teacher.getTname(),list);  //写进Excel表格当中
		        	JOptionPane.showMessageDialog(null, "信息导出成功！");
		        	
				} catch (IllegalArgumentException e1)
				{				
					e1.printStackTrace();
				} catch (IllegalAccessException e1)
				{
					e1.printStackTrace();
				} catch (WriteException e1)
				{
					e1.printStackTrace();
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		    
	        
		}
		
		else if (e.getSource() == jb4) //退出
		{
			dispose();
		}
	}
}
