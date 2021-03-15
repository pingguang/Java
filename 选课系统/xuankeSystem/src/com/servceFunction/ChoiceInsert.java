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

public class ChoiceInsert extends JDialog implements ActionListener
{
	private JLabel jlCname, jlName, jlMajor, jlClass;
	private	JTextField jtfCname, jtfName, jtfMajor, jtfClass;
	private	JPanel jp1, jp2;
	private	JButton submit, cancel;
	private	Student student;
	
	public ChoiceInsert(Frame owner,String title,boolean modal,CourseTable sm,int rowNum,Student student)
	{
			super(owner, title, modal);
	        this.student = student;
	        jlCname = new JLabel("课程名");
			jlName = new JLabel("姓名");
			jlMajor = new JLabel("专业");
			jlClass = new JLabel("班级");
		 
			jtfCname = new JTextField(10);
			jtfName = new JTextField(10);
			jtfMajor = new JTextField(10);
			jtfClass= new JTextField(10);
			// 显示选择的行数据
			jtfCname.setText(sm.getValueAt(rowNum, 0).toString());
			jtfName.setText(student.getSname());
			jtfMajor.setText(student.getSmajor());
			jtfClass.setText(student.getSclass());
		 
			submit = new JButton("确认");
			submit.addActionListener(this);// 监听submit按钮
			cancel = new JButton("取消");
			cancel.addActionListener(this);
		 
			jp1 = new JPanel();
			jp2 = new JPanel();
			init();
			setBounds(500,400,300,200);
			setVisible(true);
	}
	
    public void init()
	{
		jp2.add(submit);
		jp2.add(cancel);
		jp1.add(jlCname); jp1.add(jtfCname);
		jp1.add(jlName);  jp1.add(jtfName);
		jp1.add(jlMajor); jp1.add(jtfMajor);
		jp1.add(jlClass); jp1.add(jtfClass);
		// 设置布局
	    jp1.setLayout(new GridLayout(4, 2));
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
				String insert = "insert into choice values(?,?,?,?)";
				pstmt = conn.prepareStatement(insert);
	 
				// 获取输入框数据,并插入到数据库中	
				pstmt.setString(1, jtfCname.getText());
				pstmt.setString(2, jtfName.getText());
				pstmt.setString(3, jtfMajor.getText());
				pstmt.setString(4, jtfClass.getText());
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "选课成功！");
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
						if (pstmt != null) 
						{
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
			} else if (e.getSource() == cancel) //退出选课界面
			{
				dispose();
			}
		}
	}

