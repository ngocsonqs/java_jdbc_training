package jp.co.bangoku.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import jp.co.bangoku.jdbc.entity.Student;
import jp.co.bangoku.jdbc.utils.DBUtils;

/**
 * Studentテーブルにアクセスするクラスです。
 * Studentテーブルに各CRUD操作を実装するクラスです。
 * @author bangoku
 * @date 2016/05/13
 */
public class StudentDao {
    
    /** データベース接続 */
    private Connection conn;
    
    /** ID検索のSQL */
    private static final String FIND_BY_ID_SQL = "SELECT * FROM student WHERE id = ?";
    
    /** 全件検索のSQL */
    private static final String FIND_ALL_SQL = "SELECT * FROM student ORDER BY id";
    
    /** 指定したカラム名とそのカラム名ソート順検索のSQL */
    private static final String FIND_ALL_SORT_SQL = "SELECT * FROM student ORDER BY %s %s";
    
    /** データを挿入する SQL */
    private static final String INSERT_DATA_SQL 
        = "INSERT INTO student (id, name, birthday, class_name) VALUES (?, ?, ?, ?)";
    
    /** IDでクラス名の値をアップデート */
    private static final String UPDATE_CLASSNAME_BY_ID 
        = "UPDATE student SET class_name = ? WHERE id = ?";
    
    /** IDでデータを削除するSQL */
    private static final String DELETE_BY_ID = "DELETE FROM student WHERE id = ?";
    
    /**
     * このクラスのオブジェクトを構築します。
     * @param conn データベース接続
     */
    public StudentDao(Connection conn) {
        this.conn = conn;
    }
    
    /**
     * 指定のIDで検索します。
     * @param id ID
     * @return Studentオブジェクト、データが見つからない場合はnull
     * @throws SQLException データベース例外が発生した場合
     */
    public Student findById(int id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        Student student = null;
        try {
            pstmt = conn.prepareStatement(FIND_BY_ID_SQL);
            pstmt.setInt(1, id);
            
            // SQL実行
            rs = pstmt.executeQuery();
            
            // レコードがあればValueObjectに詰め替え
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setBirthday(rs.getDate("birthday"));
                student.setCls(StringUtils.defaultString(rs.getString("class_name"), "なし"));
            }
        } finally {
            DBUtils.close(pstmt);
            DBUtils.close(rs);
        }
        return student;
    }
    
    /**
     * 全てのデータを検索します。
     * @return Studentオブジェクト
     * @throws SQLException データへ―ス例外が発生した場合
     */
    public List<Student> findAll() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        List<Student> list = new ArrayList<Student>();
        
        try {
            pstmt = conn.prepareStatement(FIND_ALL_SQL);
            
            // SQL実行
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                //レコードがあれば、ValueObjectに詰め替え
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setBirthday(rs.getDate("birthday"));
                student.setCls(rs.getString("class_name"));
                
                //リストに追加
                list.add(student);
            }
        } finally {
            DBUtils.close(pstmt);
            DBUtils.close(rs);
        }
        return list;
    }
    
    /**
     * ソート順がキーボードから指定して、全件検索を取得します。
     * @param columnName カラム名
     * @param sortFlag 昇順の場合はTRUE, 降順の場合はFALSE
     * @return Studentオブジェクト
     * @throws SQLException　データベース例外が発生した場合
     */
    public List<Student> findAllSort(String columnName, boolean sortFlag) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        List<Student> list = new ArrayList<Student>();
        
        try {
            //sortFlag = true -> 昇順, sortFlag = false -> 降順
            String sort = sortFlag ? "ACS" : "DESC";
            String sql = String.format(FIND_ALL_SORT_SQL, columnName, sort);
            
            pstmt = conn.prepareStatement(sql);
            
            // SQLを実行します
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                // レコードがあれば、ValueObjectに詰め替え
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setBirthday(rs.getDate("birthday"));
                student.setCls(rs.getString("class_name"));
                
                // リストに追加
                list.add(student);
            }
        } finally {
            DBUtils.close(pstmt);
            DBUtils.close(rs);
        }
        return list;
    }
    
    /**
     * studentテーブルにデータを登録します
     * @param student Studentオブジェクト
     * @return 登録件数、登録されない場合は0
     * @throws SQLException 生年月日のパース例外が発生した場合
     */
    public int insert(Student student) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        int count;
        try {
            pstmt = conn.prepareStatement(INSERT_DATA_SQL);
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setDate(3, student.getBirthday());
            pstmt.setString(4, student.getCls());
            
            // insert文を実行します。
            count = pstmt.executeUpdate();
        } finally {
            DBUtils.close(pstmt);
            DBUtils.close(rs);
        }
        return count;
    }
    
    /**
     * 指定されたIDにクラス名を更新します。
     * @param student Studentオブジェクト
     * @return 更新件数、更新されない場合は0
     * @throws SQLException データベース例外が発生した場合
     */
    public int update(Student student) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // update文を実行します
        int count;
        try {
            pstmt = conn.prepareStatement(UPDATE_CLASSNAME_BY_ID);
            pstmt.setString(1, student.getCls());
            pstmt.setInt(2, student.getId());
            
            count = pstmt.executeUpdate();
        } finally {
            DBUtils.close(pstmt);
            DBUtils.close(rs);
        }
        return count;
    }
    
    /**
     * 指定されたIDにデータを削除します。
     * @param student Studentオブジェクト
     * @param isSureDelete 本当に削除したいかどうかを聞く
     * @return 削除件数、削除されたい場合は 0
     * @throws SQLException データベース例外が発生した場合
     */
    public int delete(Student student, boolean isSureDelete) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        int count = 0;
        try {
            pstmt = conn.prepareStatement(DELETE_BY_ID);
            pstmt.setInt(1, student.getId());
            
            // delete文を実行します。
            if (isSureDelete) {
                count = pstmt.executeUpdate();
            }
        } finally {
            DBUtils.close(pstmt);
            DBUtils.close(rs);
        }
        return count;
    }
}
