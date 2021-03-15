package com.servceWindow;

import com.servceFunction.*; 
import com.loginFunction.*;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.*;
import javax.swing.border.EmptyBorder;
 
public class LoginWindow extends JFrame implements ActionListener {
	private String imgPath ;
	private ImageIcon img ;
	private JLabel label ;
    private JPanel pan ;
    private JLabel namelab; 
    private JLabel passlab ;
    private JLabel userkind;
    private JTextField nametext;
    private JPasswordField passtext;
    private JComboBox usertext;
    private Font font;
    public JButton denglu ;
    public JButton zhuce;
 
    public LoginWindow() {
    	imgPath = "C:\\Users\\27257\\Desktop\\pg.jpg";    //登录界面的背景图片
    	 img = new ImageIcon(imgPath);
    	 label = new JLabel(img);
         pan = new JPanel();
         namelab = new JLabel("用户名");
         passlab = new JLabel("密    码");
         userkind = new JLabel("身   份");
         nametext = new JTextField();
         passtext = new JPasswordField();
         usertext = new JComboBox();
         denglu = new JButton("登录");
         zhuce = new JButton("注册");
         font = new Font("宋体", Font.BOLD, 12); 
         init();
    }
    public void init()
    {
        super.setTitle("欢迎登录选课系统");
        pan.setLayout(null);
        namelab.setBounds(20, 20, 60, 30);
        nametext.setBounds(90, 20, 140, 30);
        passlab.setBounds(20, 60, 60, 30);
        passtext.setBounds(90, 60, 140, 30);
        userkind.setBounds(20, 100, 60, 30);
        usertext.setBounds(90,100,90,30);
        usertext.addItem("学 生");
        usertext.addItem("教 师");
        usertext.addItem("管理员");
        denglu.setBounds(30, 160, 90, 20);
        zhuce.setBounds(140, 160, 90, 20);
        pan.add(namelab);
        pan.add(nametext);
        pan.add(passlab);
        pan.add(passtext);
        pan.add(userkind);
        pan.add(usertext);
        pan.add(denglu);
        pan.add(zhuce);
        passtext.setFont(font);
        zhuce.setFont(font);
        denglu.addActionListener(this);
        zhuce.addActionListener(this);
        super.add(pan);
        super.setSize(500, 400);
        super.setVisible(true);
        ((JPanel) super.getContentPane()).setOpaque(false);
        super.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        label.setBounds(0, 0, super.getWidth(), super.getHeight());
        pan.setOpaque(false);
        super.setContentPane(pan);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) 
    {
    	
        if (e.getSource() == denglu) 
        {
            denglu();
        } else if (e.getSource() == zhuce) 
        {
            zhuce();
        } 
    }
 
    //登录按钮的事件处理函数
    public void denglu() 
    {
    	StudentWindow stuWin; 
    	TeacherWindow teaWin;
    	AdminWindow adWin;
    	LoginCheck d = new LoginCheck();
        String username = nametext.getText();
        String password = passtext.getText();
        String userShenfen = (String) usertext.getSelectedItem();
        if (d.compare(userShenfen,username, password))
        { 
			if(userShenfen=="学 生")
               stuWin=new StudentWindow(username);   //进入学生端
       	    else if(userShenfen=="教 师")
       	       teaWin= new TeacherWindow(username);  //进入教师端
       	    else if(userShenfen=="管理员")
               adWin = new AdminWindow();            //进入管理员端
			super.setVisible(false);
        }
    }
 
    public void zhuce() //用户注册
    {    
    	LoginCheck d = new LoginCheck();   // 登录注册函数类
        String username = nametext.getText();    //获得账号框中的信息
        String password = passtext.getText();    //获得密码框中的信息
        String userShenfen = (String) usertext.getSelectedItem();
        if(userShenfen=="管理员")   //管理员不能注册的
        {
        	JOptionPane.showMessageDialog(null, "管理员不能注册！");
        }
        else    //注册时只需要账号，密码即可，待用户登录后再完善信息
        {
        	System.out.println("输出："+userShenfen);
            d.insert(userShenfen,username, password);   //根据用户的身份进行注册
        }    
    }
}

