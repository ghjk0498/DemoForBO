package com.example.demo.fourth;

import com.example.demo.second.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@Service("JDBCService")
@RequiredArgsConstructor
public class JDBCService implements DBService {
	
	private final DataSource dataSource;

    @Override
    public int insertUsers(CustomUser user) {
        String sql = "insert into demo_for_bo.users values(?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
            int n = pstmt.executeUpdate();
            conn.commit();
            return n;
        } catch (Exception e) {
        	rollback(conn);
        } finally {
            close(conn);
            close(pstmt);
        }
		return -1;
    }
    
    @Override
    public List<CustomUser> selectUsers() {
    	String sql = "select * from demo_for_bo.users";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CustomUser> list = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
            	list.add(new CustomUser(
            				rs.getString(0),
	            			rs.getString(1),
	            			rs.getString(2)
            			));
            }
            conn.commit();
        } catch (Exception e) {
        	rollback(conn);
        } finally {
        	close(conn);
            close(pstmt);
            close(rs);
        }
        return list;
    }
    
    public void close(Connection conn) {
    	if (conn != null) {
    		try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void close(PreparedStatement pstmt) {
    	if (pstmt != null) {
    		try {
    			pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void close(ResultSet rs) {
    	if (rs != null) {
    		try {
    			rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void rollback(Connection conn) {
    	try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
