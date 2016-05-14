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
 * Student�e�[�u���ɃA�N�Z�X����N���X�ł��B
 * Student�e�[�u���ɊeCRUD�������������N���X�ł��B
 * @author bangoku
 * @date 2016/05/13
 */
public class StudentDao {
    
    /** �f�[�^�x�[�X�ڑ� */
    private Connection conn;
    
    /** ID������SQL */
    private static final String FIND_BY_ID_SQL = "SELECT * FROM student WHERE id = ?";
    
    /** �S��������SQL */
    private static final String FIND_ALL_SQL = "SELECT * FROM student ORDER BY id";
    
    /** �w�肵���J�������Ƃ��̃J�������\�[�g��������SQL */
    private static final String FIND_ALL_SORT_SQL = "SELECT * FROM student ORDER BY %s %s";
    
    /** �f�[�^��}������ SQL */
    private static final String INSERT_DATA_SQL 
        = "INSERT INTO student (id, name, birthday, class_name) VALUES (?, ?, ?, ?)";
    
    /** ID�ŃN���X���̒l���A�b�v�f�[�g */
    private static final String UPDATE_CLASSNAME_BY_ID 
        = "UPDATE student SET class_name = ? WHERE id = ?";
    
    /** ID�Ńf�[�^���폜����SQL */
    private static final String DELETE_BY_ID = "DELETE FROM student WHERE id = ?";
    
    /**
     * ���̃N���X�̃I�u�W�F�N�g���\�z���܂��B
     * @param conn �f�[�^�x�[�X�ڑ�
     */
    public StudentDao(Connection conn) {
        this.conn = conn;
    }
    
    /**
     * �w���ID�Ō������܂��B
     * @param id ID
     * @return Student�I�u�W�F�N�g�A�f�[�^��������Ȃ��ꍇ��null
     * @throws SQLException �f�[�^�x�[�X��O�����������ꍇ
     */
    public Student findById(int id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        Student student = null;
        try {
            pstmt = conn.prepareStatement(FIND_BY_ID_SQL);
            pstmt.setInt(1, id);
            
            // SQL���s
            rs = pstmt.executeQuery();
            
            // ���R�[�h�������ValueObject�ɋl�ߑւ�
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setBirthday(rs.getDate("birthday"));
                student.setCls(StringUtils.defaultString(rs.getString("class_name"), "�Ȃ�"));
            }
        } finally {
            DBUtils.close(pstmt);
            DBUtils.close(rs);
        }
        return student;
    }
    
    /**
     * �S�Ẵf�[�^���������܂��B
     * @return Student�I�u�W�F�N�g
     * @throws SQLException �f�[�^�ց\�X��O�����������ꍇ
     */
    public List<Student> findAll() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        List<Student> list = new ArrayList<Student>();
        
        try {
            pstmt = conn.prepareStatement(FIND_ALL_SQL);
            
            // SQL���s
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                //���R�[�h������΁AValueObject�ɋl�ߑւ�
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setBirthday(rs.getDate("birthday"));
                student.setCls(rs.getString("class_name"));
                
                //���X�g�ɒǉ�
                list.add(student);
            }
        } finally {
            DBUtils.close(pstmt);
            DBUtils.close(rs);
        }
        return list;
    }
    
    /**
     * �\�[�g�����L�[�{�[�h����w�肵�āA�S���������擾���܂��B
     * @param columnName �J������
     * @param sortFlag �����̏ꍇ��TRUE, �~���̏ꍇ��FALSE
     * @return Student�I�u�W�F�N�g
     * @throws SQLException�@�f�[�^�x�[�X��O�����������ꍇ
     */
    public List<Student> findAllSort(String columnName, boolean sortFlag) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        List<Student> list = new ArrayList<Student>();
        
        try {
            //sortFlag = true -> ����, sortFlag = false -> �~��
            String sort = sortFlag ? "ACS" : "DESC";
            String sql = String.format(FIND_ALL_SORT_SQL, columnName, sort);
            
            pstmt = conn.prepareStatement(sql);
            
            // SQL�����s���܂�
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                // ���R�[�h������΁AValueObject�ɋl�ߑւ�
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setBirthday(rs.getDate("birthday"));
                student.setCls(rs.getString("class_name"));
                
                // ���X�g�ɒǉ�
                list.add(student);
            }
        } finally {
            DBUtils.close(pstmt);
            DBUtils.close(rs);
        }
        return list;
    }
    
    /**
     * student�e�[�u���Ƀf�[�^��o�^���܂�
     * @param student Student�I�u�W�F�N�g
     * @return �o�^�����A�o�^����Ȃ��ꍇ��0
     * @throws SQLException ���N�����̃p�[�X��O�����������ꍇ
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
            
            // insert�������s���܂��B
            count = pstmt.executeUpdate();
        } finally {
            DBUtils.close(pstmt);
            DBUtils.close(rs);
        }
        return count;
    }
    
    /**
     * �w�肳�ꂽID�ɃN���X�����X�V���܂��B
     * @param student Student�I�u�W�F�N�g
     * @return �X�V�����A�X�V����Ȃ��ꍇ��0
     * @throws SQLException �f�[�^�x�[�X��O�����������ꍇ
     */
    public int update(Student student) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // update�������s���܂�
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
     * �w�肳�ꂽID�Ƀf�[�^���폜���܂��B
     * @param student Student�I�u�W�F�N�g
     * @param isSureDelete �{���ɍ폜���������ǂ����𕷂�
     * @return �폜�����A�폜���ꂽ���ꍇ�� 0
     * @throws SQLException �f�[�^�x�[�X��O�����������ꍇ
     */
    public int delete(Student student, boolean isSureDelete) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        int count = 0;
        try {
            pstmt = conn.prepareStatement(DELETE_BY_ID);
            pstmt.setInt(1, student.getId());
            
            // delete�������s���܂��B
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
