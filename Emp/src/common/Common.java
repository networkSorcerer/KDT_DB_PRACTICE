package common;

import java.sql.*;

public class Common {
    final static String ORACLE_URL ="jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    final static String ORACLE_ID = "scott";
    final static String ORACLE_PW = "tiger";
    final static String ORACLE_DRV = "oracle.jdbc.driver.OracleDriver";

    public static Connection getConnection() {
        Connection conn = null;
        try{
            Class.forName(ORACLE_DRV);
            conn = DriverManager.getConnection(ORACLE_URL,ORACLE_ID,ORACLE_PW);
            System.out.println("Connection 생성 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn) {
        try{
            if(conn != null && !conn.isClosed()){
                conn.close();
                System.out.println("Connection 해제 성공");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
                System.out.println("Statement 해제 성공");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rset) {
        try{
            if(rset != null && !rset.isClosed()){
                rset.close();
                System.out.println("Resul set 해제 성공");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commit(Connection conn) {
        try{
            if(conn != null && !conn.isClosed()){
                conn.commit();
                System.out.println("커밋 완료");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void rollback(Connection conn) {
        try{
            if(conn != null && !conn.isClosed()){
                conn.rollback();
                System.out.println("롤백 완료");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
