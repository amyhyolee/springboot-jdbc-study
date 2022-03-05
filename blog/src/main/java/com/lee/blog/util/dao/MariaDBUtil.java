package com.lee.blog.util.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MariaDBUtil {
	
	public Connection getConnection() throws Exception{
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/analysis","analysis", "analysis");
		return conn;
	}
	
	public List<Map<String,String>> getList() throws Exception {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String,String>> items = null; 
				
		try {
			conn = this.getConnection();
			
			pstmt = conn.prepareStatement("select * from chl_acdt_info");
			rs = pstmt.executeQuery();
			items = new ArrayList<>();
			while(rs.next()) {
				Map<String,String> m = new HashMap<>();
				m.put("SIDO_SGG_NM", rs.getString("SIDO_SGG_NM"));// 지점명
				m.put("LO_CRD", rs.getString("LO_CRD"));// 경도
				m.put("LA_CRD", rs.getString("LA_CRD"));// 위도
				m.put("GEOM_JSON", rs.getString("GEOM_JSON"));//다발지역폴리곤
				items.add(m);
				System.out.println(m);
			}
		} catch(Exception e) {
			e.getStackTrace();
		} finally {
            if(rs != null) {
                rs.close(); // 선택 사항
            }
            
            if(pstmt != null) {
                pstmt.close(); // 선택사항이지만 호출 추천
            }
        
            if(conn != null) {
                conn.close(); // 필수 사항
            }
		}
		return items;
	}
	
	public static void main(String...args) throws Exception{
		MariaDBUtil dao = new MariaDBUtil();
		
		System.out.println(dao.getList());
	}
}