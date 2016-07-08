package com.fq.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

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


	private static String getQueryURL(HttpServletRequest request) {
		String res = request.getRequestURI();
		@SuppressWarnings("unchecked")
		Enumeration<Object> s = request.getParameterNames();
		String queryStr = "";
		int i = 0;
		while (s.hasMoreElements()) {
			String key = s.nextElement().toString();
			if (key.equals("page")) {
				continue;
			} else {
				if (i == 0) {
					queryStr += key + "=" + request.getParameter(key);
				} else {
					queryStr += "&" + key + "=" + request.getParameter(key);
				}
			}
			i++;
		}
		if (queryStr.equals("")) {
			return res + "?page=";
		} else {
			return res + "?" + queryStr + "&page=";
		}

	}

	/*
	 * 查询sql返回类型
	 */
	public static List<List<String>> query2List(String sql) throws Exception {
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

	/*
	 * 判断是否存在
	 */

	public static boolean isHas(String sql) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = getConnect();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				closeRPC(conn, rs, pst);
				return true;
			} else {
				closeRPC(conn, rs, pst);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			closeRPC(conn, rs, pst);
			return true;
			// TODO: handle exception
		}
	}

	/*
	 * 返回一行数据
	 */
	public static Map<String, String> queryMap(String sql) throws Exception {
		Map<String, String> res = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = getConnect();
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while (rs.next()) {
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				res.put(rsmd.getColumnLabel(i), rs.getString(i) == null?"":rs.getString(i));
			}
			break;
		}
		closeRPC(conn, rs, pst);
		return res;
	}

	/*
	 * 执行SQL
	 */
	public static List<Map<String, String>> queryList(String sql)
			throws Exception {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = getConnect();
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while (rs.next()) {
			Map<String, String> map = new HashMap<String, String>();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String key = rsmd.getColumnLabel(i);
				String value = rs.getString(i)==null?"":rs.getString(i);
				map.put(key, value);
			}
			result.add(map);
		}
		closeRPC(conn, rs, pst);
		return result;
	}

	/**
	 * 执行SQL
	 * 
	 * @param data
	 *            插入的列,值
	 * @param tableName
	 *            表名
	 * @param type
	 *            执行方式 insert . update . delete
	 * @param where
	 *            执行update . delete 时条件
	 * @return 执行sql id
	 * @throws Exception
	 */
	public static int execute(Map<String, String> data, String tableName,
			String type, Map<String, String> where) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int id = 0;
		// 如果是update 却掉data中包含where的参数
		if (type.equals("update")) {
			for (Map.Entry<String, String> entry : where.entrySet()) {
				if (data.containsKey(entry.getKey())) {
					data.remove(entry.getKey());
				}
			}
		}
		String sql = (String) creSql(data, tableName, type, where).get("sql");
		String[] param = (String[]) creSql(data, tableName, type, where).get(
				"param");
		conn = getConnect();
		pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				pst.setObject(i + 1, param[i]);
			}
		}
		pst.execute();
		rs = pst.getGeneratedKeys();
		if (rs != null && rs.next()) {
			id = rs.getInt(1);
		}
		closeRPC(conn, rs, pst);
		return id;
	}

	public static String whereParse(Map<String, String> where) {
		String wh = "";
		int i = 0;
		for (Map.Entry<String, String> entry : where.entrySet()) {
			if (i != 0) {
				wh += ", " + entry.getKey() + " = ? ";
			} else {
				wh += entry.getKey() + " = ? ";
			}
			i++;
		}
		return wh;
	}

	public static String[] addValues(String[] values, Map<String, String> where) {
		String[] nVal = new String[values.length + where.size()];
		for (int i = 0; i < values.length; i++) {
			nVal[i] = values[i];
		}
		int st = values.length;
		for (Map.Entry<String, String> entry : where.entrySet()) {
			nVal[st] = entry.getValue();
			st++;
		}
		return nVal;
	}

	/**
	 * 拼接SQL
	 * 
	 * @param data
	 * @param tableName
	 * @param type
	 * @param where
	 * @return
	 */
	public static Map<String, Object> creSql(Map<String, String> data,
			String tableName, String type, Map<String, String> where) {
		String[] column = new String[data.size()];
		String[] values = new String[data.size()];
		int i = 0;
		for (Map.Entry<String, String> entry : data.entrySet()) {
			values[i] = entry.getValue();
			column[i] = entry.getKey();
			i++;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "";
		if (type.equals("insert")) {
			String col = "";
			String val = "";
			for (int k = 0; k < column.length; k++) {
				if (k != 0) {
					col += "," + column[k];
					val += ",?";
				} else {
					col += column[k];
					val += "?";
				}
			}
			sql = " insert into " + tableName + " (" + col + ") values (" + val
					+ ")  ";
			map.put("sql", sql);
			map.put("param", values);
			return map;
		}

		if (type.equals("update")) {
			String col = "";
			for (int k = 0; k < column.length; k++) {
				if (k != 0) {
					col += "," + column[k] + "=?";
				} else {
					col += column[k] + " =? ";
				}
			}
			sql = "update " + tableName + " set " + col + " where  "
					+ whereParse(where) + " ";
			String[] nVal = addValues(values, where);
			map.put("sql", sql);
			map.put("param", nVal);
		}
		if (type.equals("delete")) {
			sql = " delete from " + tableName + " where " + whereParse(where);
			String[] nVal = addValues(new String[0], where);
			map.put("sql", sql);
			map.put("param", nVal);
		}
		return map;
	}

	public static boolean executeSqls(String[] sql) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int flag = 1;
		conn = getConnect();
		conn.setAutoCommit(false);
		for (int i = 0; i < sql.length; i++) {
			pst = conn.prepareStatement(sql[i]);
			flag = pst.executeUpdate();
		}
		conn.commit();
		closeRPC(conn, rs, pst);
		if (flag >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 执行sql[] 返回每一个执行后的id
	 */
	public static int[] insertSqls(String[] sql) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int[] ids = new int[sql.length];// 保存生成的ID
		conn = getConnect();
		conn.setAutoCommit(false);
		boolean flag = false;
		for (int i = 0; i < sql.length; i++) {
			pst = conn
					.prepareStatement(sql[i], Statement.RETURN_GENERATED_KEYS);
			pst.execute();
			rs = pst.getGeneratedKeys();
			if (rs != null && rs.next()) {
				ids[i] = rs.getInt(1);
			}
			if (ids[i] >= 0) {
				flag = true;
			}
		}
		if (flag) {
			conn.commit();
		} else {
			conn.rollback();
		}
		return ids;
	}

	public static int executesql(String sql, String[] para) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int id = 0;// 保存生成的ID
		conn = getConnect();
		pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if (para != null) {
			for (int i = 0; i < para.length; i++) {
				pst.setObject(i + 1, para[i]);
			}
		}
		pst.execute();
		rs = pst.getGeneratedKeys();
		if (rs != null && rs.next()) {
			id = rs.getInt(1);
		}
		return id;
	}

	public static String getLimitStr(int page) {
		int start = 0;
		int end = 10;
		if (page <= 0) {
			start = 0;
		} else {
			start = (page - 1) * 10;
		}
		String limit = " limit " + start + ", " + end + " ";
		return limit;

	}

	public static List<Map<String, String>> queryPageData(String sql, int page) throws Exception{
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
			conn = getConnect();
			sql += getLimitStr(page);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				for (int i = 1; i <= count; i++) {
					String key = rsmd.getColumnLabel(i);
					String value = rs.getString(i);
					map.put(key, value);
				}
				result.add(map);
			}
			closeRPC(conn, rs, pst);
			return result;
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
	/**
	 * 执行没有参数返回到存储过程
	 * @param proceduresName
	 */
	public static void callProcedures (String proceduresName)throws Exception {
		Connection conn = getConnect();
		String sql = "{call "+proceduresName+"}";
		CallableStatement callableStatement = conn.prepareCall(sql);
		callableStatement.executeUpdate();
		callableStatement.close();
		conn.close();
	}
	public static void main(String[] args) throws Exception{
		callProcedures("compute_interest");
	}
	
	private static String getSQL(String targetStr, Object[] sqlValues) {
		StringTokenizer token = new StringTokenizer(targetStr, "?", false);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < sqlValues.length; i++) {
			buf.append(token.nextToken());
			buf.append("'" + sqlValues[i] + "'");
		}
		return buf.toString();
	}
}
