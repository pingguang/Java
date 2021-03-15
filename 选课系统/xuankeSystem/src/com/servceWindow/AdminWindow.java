package com.servceWindow;

import com.servceFunction.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;

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

public class AdminWindow extends JFrame implements ActionListener
{
	
	private	JPanel jp;//定义控件
	private	JButton jb,jx;
	private	JTable jt;	//表格
	private	JScrollPane jsp;	
	private	CourseTable sm;
			
	public AdminWindow() 
	{
		init();
		super.setTitle("欢迎进入管理员端");
		super.setSize(800, 550);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	 
	private void init() 
	{
		jp = new JPanel();
		jb = new JButton("退出登录");
		jx = new JButton("审核");
		jb.addActionListener(this);//监听jb按钮
		jx.addActionListener(this);//监听jx按钮
		jp.add(jb);
		jp.add(jx);
			
		sm = new CourseTable();
		jt = new JTable(sm);
		jsp = new JScrollPane(jt);
		this.add(jsp);
		this.add(jp,"South");
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == jx)   //课程审核
		{
			String cname = null;
			int rowNum = jt.getSelectedRow();
			if (rowNum == -1) 
			{
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			cname = sm.getValueAt(rowNum,0).toString();//获得课程名称
			String Cexcamine = sm.getValueAt(rowNum,6).toString();  //获得审核状态
			Integer x =Integer.valueOf(Cexcamine);   //转化为int类型
			if(x==1)  //如果说审核已经通过	
			{
				JOptionPane.showMessageDialog(this, "该课程已经通过审核，不能重复操作！");
				return;
			}
			else 
			{
				CourseUpdate.update(cname);
				JOptionPane.showMessageDialog(null, "审核通过！");
				sm = new CourseTable();
			    jt.setModel(sm);  //管理员界面表格数据更新
			}
			
		}
		else if (e.getSource() == jb) //退出登录
		{
			dispose();
		}
	}
}
