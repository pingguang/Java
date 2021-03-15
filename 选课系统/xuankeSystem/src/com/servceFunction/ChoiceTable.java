package com.servceFunction;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ChoiceTable extends AbstractTableModel 
{
	private Vector rowData;	//存放数据行
	private Vector columnNames;	//存放列名
	private Statement stmt = null;//数据库连接变量
	private Connection conn = null;
	private ResultSet rs = null;
	private String sql;
	
	public ChoiceTable(String sql)  //带参构造函数
	{
		this.sql = sql;
		init();
	}
	
	public ChoiceTable()  //不带参构造函数
	{
		this.sql = "select * from choice";
		init();
	}
 
	private void init() {
		columnNames = new Vector();
		//设置列名
		columnNames.add("课程名");
		columnNames.add("姓名");
		columnNames.add("专业");
		columnNames.add("班级");
		//获取数据行
		rowData = new Vector();
		try {
			//加载驱动
			Class.forName("org.gjt.mm.mysql.Driver");//加载驱动
			String url = "jdbc:mysql://localhost:3306/xuanke";//数据库连接的地址
			String user = "root";
			String password = "root";
			conn = (Connection) DriverManager.getConnection(url, user, password);
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(sql);
			//获取查询数据，并放入vector
			while(rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				//放入rowData中,相当于二维数组
				rowData.add(hang);
			}	
		  } catch(Exception e) 
		   {
			  e.printStackTrace();
		   } finally 
		   {
			  try {
				if (rs != null) 
				{
					rs.close();
					rs = null;
				}				
				if (stmt != null) 
				{
					stmt.close();
					stmt = null;
				}				
				if (conn != null)
				{
					conn.close();
					conn = null;
				}	
			} catch(Exception e)
			  {
				e.printStackTrace();
			}
		}
	}
	public int getRowCount() {
		return rowData.size();
	}

	public int getColumnCount() {
		return columnNames.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((Vector)(rowData.get(rowIndex))).get(columnIndex);
	}
	
	public String getColumnName(int columnIndex) {
		return (String)columnNames.get(columnIndex);
	}
}
