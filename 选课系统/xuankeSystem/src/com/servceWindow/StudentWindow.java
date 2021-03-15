package com.servceWindow;

import com.typeBase.*;
import com.servceFunction.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class StudentWindow extends JFrame implements ActionListener
{ 
	private JPanel jp1,jp2;//定义控件
	private JLabel jl1,jl2;
	private JButton jb1,jb2,jb3,jb4;
	private JTable jt;	  //表格
	private JScrollPane jsp;
	private JTextField jtf;
	private CourseTable su;  //课程表格
	private String user;
	private String sql;
	private Student student; 
	private GetStudent gs;
	
	public StudentWindow(String username) 
	{
		this.user = username;
		 gs = new GetStudent();
		this.student=gs.getStudent(user);   //获得当前用户的全部信息
		init();
		super.setTitle("欢迎进入学生端");
		this.setBounds(100, 200,800, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		toFill_the_information();
	}
 
	private void init() 
	{
		jp1 = new JPanel();
		jtf = new JTextField(15);
		jb1 = new JButton("选课查询");
		jb1.addActionListener(this);            //监听jb1按钮
		jl1 = new JLabel("请输入教师姓名：");	//按课教师姓名查询
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		jb2 = new JButton("修改个人信息");
		jb2.addActionListener(this);//插入监听jb2按钮代码
		jb3 = new JButton("选课");	
		jb3.addActionListener(this);//插入监听jb3按钮代码
		jb4 = new JButton("退出登录");
		jb4.addActionListener(this);//插入监听jb4按钮代码
		jp2 = new JPanel();
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		su = new CourseTable();
		jt = new JTable(su);
		jsp = new JScrollPane(jt);
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
	}
	public void toFill_the_information()
	{
		if(student.getSname().equals("0")&&student.getScontact().equals("0"))
		{
			JOptionPane.showMessageDialog(null, "请您完善您的信息！");
		}
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		
		if (e.getSource() == jb1) //课程查询
		{
			String cteach = jtf.getText().trim();
			if (cteach.equals("")) 
			{
				sql = "select * from course";    //显示所有课程的信息
			} else 
			{
				sql = "select * from course where Cteach='" + cteach + "'";  //显示查询的老师的课程信息
			}
			su = new CourseTable(sql);
			jt.setModel(su);//刷新学生端的表格的信息
		} 
		else if (e.getSource() == jb2) //修改个人信息
		{
			StudentUpdate sa = new StudentUpdate(this,"修改个人信息",true,student);
			this.student=gs.getStudent(user);   //获得当前用户的全部信息
		} 
		else if (e.getSource() == jb3) //选课
		{
			int rowNum = jt.getSelectedRow();   
            //获得课程表格的最后一项的数据，审核状态
			String Cexcamine = su.getValueAt(rowNum,6).toString(); 
			Integer x =Integer.valueOf(Cexcamine);   //转化为int类型
			if (rowNum == -1) //若鼠标没有选择表格中的某一行（鼠标点出去了）
			{
				JOptionPane.showMessageDialog(this, "请选择一行");   
				return;
			}
			if (x == 0) //若状态为未审核，则不能选择该课程
			{
				JOptionPane.showMessageDialog(this, "该课程暂时还不能选！");
				return;
			}
			ChoiceInsert cu =new ChoiceInsert(this,"选课界面",true,su,rowNum,student); //选课界面，临时界面
		}

		else if (e.getSource() == jb4)//退出登录 
		{
			dispose();
		}
	}
}

