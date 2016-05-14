package jp.co.bangoku.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBのユーティリティクラスです。
 * @author bangoku
 * @date 2016/05/12
 */
public final class DBUtils {
    
    /** JDBCドライバ名 */
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    
    /** ホスト名 */
    private static final String HOST_NAME = "localhost";
    
    /** DB名 */
    private static final String DATABASE_NAME = "java_training";
    
    /** DB接続URL */
    private static final String URL = "jdbc:mysql://" + HOST_NAME + "/" + DATABASE_NAME + "?useUnicode=true&characterEncoding=utf8&useSSL=false";
    
    /** DBユーザー名 */
    private static final String USER_NAME = "ngocsonqs";
    
    /** DBパスワード */
    private static final String PASSWORD = "y3ulasai";
    
    /**
     * データベースに接続します。
     * @return　Connectionオブジェクト
     * @throws ClassNotFoundException JDBCドライバが見つからない場合
     * @throws SQLException　接続に失敗した場合
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 1. JDBCドライバのロード
        Class.forName(JDBC_DRIVER);
        
        // 2. データベースへの接続
        Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        
        return conn;
    }
    
    /**
     * Connectionをクローズします。
     * @param conn Connectionオブジェクト
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * PreparedStatementをクローズします。
     * @param pstmt PreparedStatementオブジェクト
     */
    public static void close(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ResultSetをクローズします。
     * @param rs　ResultSetオブジェクト
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
