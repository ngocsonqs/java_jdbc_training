package jp.co.bangoku.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB�̃��[�e�B���e�B�N���X�ł��B
 * @author bangoku
 * @date 2016/05/12
 */
public final class DBUtils {
    
    /** JDBC�h���C�o�� */
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    
    /** �z�X�g�� */
    private static final String HOST_NAME = "localhost";
    
    /** DB�� */
    private static final String DATABASE_NAME = "java_training";
    
    /** DB�ڑ�URL */
    private static final String URL = "jdbc:mysql://" + HOST_NAME + "/" + DATABASE_NAME + "?useUnicode=true&characterEncoding=utf8&useSSL=false";
    
    /** DB���[�U�[�� */
    private static final String USER_NAME = "ngocsonqs";
    
    /** DB�p�X���[�h */
    private static final String PASSWORD = "y3ulasai";
    
    /**
     * �f�[�^�x�[�X�ɐڑ����܂��B
     * @return�@Connection�I�u�W�F�N�g
     * @throws ClassNotFoundException JDBC�h���C�o��������Ȃ��ꍇ
     * @throws SQLException�@�ڑ��Ɏ��s�����ꍇ
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 1. JDBC�h���C�o�̃��[�h
        Class.forName(JDBC_DRIVER);
        
        // 2. �f�[�^�x�[�X�ւ̐ڑ�
        Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        
        return conn;
    }
    
    /**
     * Connection���N���[�Y���܂��B
     * @param conn Connection�I�u�W�F�N�g
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
     * PreparedStatement���N���[�Y���܂��B
     * @param pstmt PreparedStatement�I�u�W�F�N�g
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
     * ResultSet���N���[�Y���܂��B
     * @param rs�@ResultSet�I�u�W�F�N�g
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
