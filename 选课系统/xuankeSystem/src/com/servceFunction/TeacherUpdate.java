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

public class TeacherUpdate extends JDialog implements ActionListener
{
	private JLabel jlId,jlPass, jlName, jlSex, jlAge,jlMajor,jlJob,jlTel;
	private JTextField jtfId,jtfPass, jtfName, jtfSex, jtfAge,jtfMajor,jtfJob,jtfTel;
	private JPanel jp1, jp2;
	private JButton submit, cancel;
	private Teacher teacher;
	 
	public TeacherUpdate(Frame owner, String title, boolean modal,Teacher teacher)
	{
		super(owner, title, modal);
	    this.teacher = teacher;     
		init();
		this.setBounds(500,400,400,400);
		setVisible(true);
	}
	
	public void init()
	{
		jlId = new JLabel("用户名");
		jlPass = new JLabel("密码");
		jlName = new JLabel("姓名");
		jlAge = new JLabel("年龄");
		jlSex = new JLabel("性别");
		jlMajor = new JLabel("专业");
		jlJob = new JLabel("职称");
		jlTel = new JLabel("联系电话");
		
		jtfId =new JTextField(15);
		jtfPass = new JTextField(15); 
		jtfName = new JTextField(15);
		jtfSex = new JTextField(15); 
		jtfAge = new JTextField(15);
		jtfMajor = new JTextField(15);
		jtfJob = new JTextField(15);
		jtfTel = new JTextField(15);
		// 显示选择的行数据
		jtfId.setText(teacher.getUsername());
		jtfPass.setText(teacher.getPassword());
		jtfName.setText(teacher.getTname());
		jtfSex.setText(teacher.getTsex()); 
		jtfAge.setText(teacher.getTage());
		jtfMajor.setText(teacher.getTmajor());
		jtfJob.setText(teacher.getTposition());
		jtfTel.setText(teacher.getTcontact());
		submit = new JButton("修改");
		submit.addActionListener(this);// 监听submit按钮
		cancel = new JButton("取消");
		cancel.addActionListener(this);
		jp1 = new JPanel();
		jp2 =new JPanel();
		// 设置布局
		jp1.setLayout(new GridLayout(8, 2));
		jp2.add(submit);
		jp2.add(cancel);
		jp1.add(jlId);
		jp1.add(jtfId);
		jp1.add(jlPass);
		jp1.add(jtfPass);
		jp1.add(jlName);
		jp1.add(jtfName);
		jp1.add(jlAge);
		jp1.add(jtfAge);
		jp1.add(jlSex);
		jp1.add(jtfSex);
		jp1.add(jlMajor);
		jp1.add(jtfMajor);
		jp1.add(jlJob);
		jp1.add(jtfJob);
		jp1.add(jlTel);
		jp1.add(jtfTel);
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
					String update = "update teacher set Password=?,Tname=?,Tage=?,Tsex=?,Tmajor=?,Tposition=?,Tcontact=?  where Username='" + teacher.getUsername() + "'";
					pstmt = conn.prepareStatement(update);
					// 获取输入框数据,并插入到数据库中
					pstmt.setString(1, jtfPass.getText());
					pstmt.setString(2, jtfName.getText());
					pstmt.setString(3, jtfAge.getText());
					pstmt.setString(4, jtfSex.getText());
					pstmt.setString(5, jtfMajor.getText());
					pstmt.setString(6, jtfJob.getText());
					pstmt.setString(7, jtfTel.getText());
					pstmt.executeUpdate();// 存入数据库
					JOptionPane.showMessageDialog(null, "信息修改成功！");
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
			} 
		 else if (e.getSource() == cancel)//退出个人信息修改界面
		{
				dispose();
		}
	}

}
