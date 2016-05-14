package jp.co.bangoku.jdbc.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jp.co.bangoku.jdbc.dao.StudentDao;
import jp.co.bangoku.jdbc.entity.Student;
import jp.co.bangoku.jdbc.utils.DBUtils;

/**
 * CRUD����̃��j���[��\�����Ĕԍ��őI�����܂��B
 * @author bangoku
 * @date 2016/05/14
 */
public class CRUD_Menu {

    /** �R�l�N�V���� */
    private static Connection conn = null;
    
    /** ID�Ō������邽�߂̑I������ԍ� */
    private static final int SELECT_BY_ID = 1;
    
    /** �S���������邽�߂̑I������ԍ� */
    private static final int SELECT_ALL = 2;
    
    /**
     * ���C��
     * @param args�@���C���I�v�V����
     */
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        Boolean showMenuFlag = true;
        
        do {
            showMenu();
            try {
                int selectNum = scan.nextInt();
                scan.nextLine();
                switch (selectNum) {
                        case 1:
                            find();
                            break;
                        case 2:
                            insert();
                            break;
                        case 3:
                            update();
                            break;
                        case 4:
                            delete();
                            break;
                        case 5:
                        default:
                            showMenuFlag = false;
                            break;
                }
            } catch (InputMismatchException e) {
                System.err.println("�y�G���[�z ���͂����������͐���������܂���B\n");
                showMenuFlag = true;
                scan.nextLine();
            }
        } while (showMenuFlag == true);
        scan.close();
    }
    
    /**
     * �eCRUD����̃��j���[
     */
    public static void showMenu() {
        System.out.println("--------CRUD���상�j���[-----------");
        System.out.println("1. �ꗗ");
        System.out.println("2. �o�^");
        System.out.println("3. �X�V");
        System.out.println("4. �폜");
        System.out.println("5. �I��");
        System.out.print("[1~5]�̂����ꂩ�̐�������͂��Ă��瑀�삵�Ă�������: ");
    }
    
    /**
     * �f�[�^�������@�̃��j���[
     */
    public static void subMenuOfFind() {
        System.out.println("�����V�X�e�����J�n���܂����B");
        System.out.println("1. ID�Ō���");
        System.out.println("2. �S������");
        System.out.print("�ǂ���̂ق��������������ł����B");
    }
    
    /**
     * �f�[�^����
     */
    public static void find() {
        // �����̃T�u���j���[��\�����܂��B
        subMenuOfFind();
        
        Scanner scan = new Scanner(System.in);
        try {
            int selectNum = scan.nextInt();
            scan.nextLine();
            
            if (selectNum == SELECT_BY_ID) {
                findById();
            } else if (selectNum == SELECT_ALL) {
                findAll();
            } else {
                System.err.println("�y�G���[�z ���͂����������͐���������܂���B\n");
            }
        } catch (InputMismatchException e) {
            System.err.println("�y�G���[�z ���͂���������������������܂���B");
        }
    }
    
    /**
     * ID�Ńf�[�^���������܂��B
     */
    public static void findById() {
        Scanner scan = new Scanner(System.in);
        Validate validate = new Validate();
        
        try {
            // DB�ڑ�
            conn = DBUtils.getConnection();
            
            // StudentDao����
            StudentDao studentDao = new StudentDao(conn);
            
            // ID����
            System.out.print("ID����͂��Ă��������F ");
            String id = scan.nextLine();
            
            // ���͂�����ID���L���ł��邩�ǂ������`�F�b�N���܂�
            if (!validate.checkId(id)) {
                System.err.println(validate.getIdMsg());
            } else {
                // ���͂�����ID�Ō������܂��B
                Student student = studentDao.findById(Integer.parseInt(id));
                
                // ���ʏo��
                if (student != null) {
                    System.out.println("-----------------------");
                    System.out.println(student.toString());
                    System.out.println("-----------------------");
                } else {
                    System.out.println("�Y���̃f�[�^������܂���ł����B");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC�h���C�o�����[�h�ł��܂���ł����B");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL��O���������܂����B");
            e.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }
    }
    
    /**
     * �\�[�g�����w�肵�đS���������܂��B
     */
    public static void findAll() {
        Scanner scan = new Scanner(System.in);
        Validate validate = new Validate();
        
        try {
            // DB�ڑ�
            conn = DBUtils.getConnection();
            
            // StudentDao����
            StudentDao studentDao = new StudentDao(conn);
            
            // �L�[�{�[�h����J�����������
            System.out.print("�J����������͂��Ă�������: ");
            String columnName = scan.nextLine();
            
            // ���͂������J��������student�e�[�u���ɑ��݂��Ă��邩�ǂ������`�F�b�N���܂��B
            if (!validate.checkColumnName(columnName)) {
                System.err.println(validate.getColumnNameMsg());
            } else {
                
                // �L�[�{�[�h����ǂ̃\�[�g�����������������
                System.out.print("�w�肵���J�����͍~��(false)������(true)������͂��Ă��������F ");
                Boolean sortFlag = scan.nextBoolean();
                
                //�@�w�肵���J�������ƃ\�[�g���őS������
                List<Student> list = studentDao.findAllSort(columnName, sortFlag);
                
                // ���ʕ\��
                for (Student student : list) {
                    System.out.println("------------------------");
                    System.out.println(student.toString());
                    System.out.println("------------------------");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC�h���C�o�����[�h�ł��܂���ł����B");
        } catch (SQLException e) {
            System.out.println("SQL��O���������܂����B");
        } finally {
            DBUtils.close(conn);
        }
    }
    
    /**
     * �f�[�^��o�^���܂��B
     */
    public static void insert() {
        Scanner scan = new Scanner(System.in);
        Validate validate = new Validate();
        try {
            // DB�ڑ�
            conn = DBUtils.getConnection();
            
            // StudentDao����
            StudentDao studentDao = new StudentDao(conn);
            
            // Student����
            Student student = new Student();
            
            // ID����
            System.out.print("ID����͂��Ă��������F ");
            String id = scan.nextLine();
            
            //���͂�����ID���L���ł��邩�ǂ������`�F�b�N���܂��B
            if (!validate.checkId(id)) {
                System.err.println(validate.getIdMsg());
            } else {
                student.setId(Integer.parseInt(id));
                
                // ���O����
                System.out.print("���O����͂��Ă�������: ");
                String name = scan.nextLine();
                
                // ���͂��������O���L���ł��邩�ǂ������`�F�b�N���܂��B
                if (!validate.checkName(name)) {
                    System.err.println(validate.getNameMsg());
                } else {
                    student.setName(name);
                    
                    // ���N��������
                    System.out.print("���N��������͂��Ă�������: ");
                    String bday = scan.nextLine();
                    
                    // ���͂��������N�������L���ł��邩�ǂ������`�F�b�N���܂��B
                    if (!validate.checkBirthday(bday)) {
                        System.err.println(validate.getBirthdayMsg());
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        java.util.Date birthday = sdf.parse(bday);
                        student.setBirthday(new java.sql.Date(birthday.getTime()));
                        
                        // �N���X������
                        System.out.print("�N���X������͂��Ă�������: ");
                        String cls = scan.nextLine();
                        
                        // ���͂������N���X�����L���ł��邩�ǂ������`�F�b�N���܂��B
                        if (!validate.checkClassName(cls)) {
                            System.err.println(validate.getClassNameMsg());
                        } else {
                            student.setCls(cls);
                            
                            // insert SQL�����s���܂�
                            int count = studentDao.insert(student);
                            
                            // ���ʏo��
                            System.out.println(count + "���o�^���܂����B");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC�h���C�o�����[�h�ł��܂���ł����B");
        } catch (SQLException e) {
            System.err.println("SQL��O���������܂����B");
        } catch (ParseException e) {
            System.err.println("�a�����̃p�[�X�Ɏ��s���܂����B");
        } finally {
            DBUtils.close(conn);
        }
    }
    
    /**
     * �f�[�^���X�V���܂��B
     */
    public static void update() {
        Scanner scan = new Scanner(System.in);
        Validate validate = new Validate();
        
        try {
            // DB�ڑ�
            conn = DBUtils.getConnection();
            
            // StudentDao����
            StudentDao studentDao = new StudentDao(conn);
            
            // Student����
            Student student = new Student();
            
            System.out.println("�X�V�V�X�e�����J�n���܂����B");
            
            // ID����
            System.out.print("ID����͂��Ă�������: ");
            String id = scan.nextLine();
            
            // ���͂�����ID���L���ł��邩�ǂ������`�F�b�N���܂��B
            if (!validate.checkId(id)) {
                System.err.println(validate.getIdMsg());
            } else {
            
                // ���͂�����ID�ɑ΂��Č���DB�ɑ��݂��Ă��邩�ǂ������`�F�b�N���܂��B
                student = studentDao.findById(Integer.parseInt(id));
                if (student == null) {
                    System.err.println("���͂�����ID�͌��݃e�[�u���ɑ��݂��Ă��܂���B");
                } else {
                    student.setId(Integer.parseInt(id));
                    
                    // �N���X������
                    System.out.print("�X�V�������N���X������͂��Ă�������: ");
                    String cls = scan.nextLine();
                    
                    // ���͂������N���X�����L���ł��邩�ǂ������`�F�b�N���܂��B
                    if (!validate.checkClassName(cls)) {
                        System.err.println(validate.getClassNameMsg());
                    } else {
                        student.setCls(cls);
                        
                        // update SQL�����s���܂�
                        int count = studentDao.update(student);
                        
                        //�@���ʏo��
                        System.out.println(count + "���X�V���܂����B");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC�h���C�o�����[�h�ł��܂���ł����B");
        } catch (SQLException e) {
            System.err.println("SQL��O���������܂����B");
        } finally {
            DBUtils.close(conn);
        }
    }
    
    /**
     * �f�[�^���폜���܂��B
     */
    public static void delete() {
        Scanner scan = new Scanner(System.in);
        Validate validate = new Validate();
        
        try {
            // DB�ڑ�
            conn = DBUtils.getConnection();
            
            // StudentDao����
            StudentDao studentDao = new StudentDao(conn);
            
            System.out.println("�폜�V�X�e�����J�n���܂����B");
            
            // ID����
            System.out.print("�폜ID����͂��Ă�������: ");
            String id = scan.nextLine();
            
            // ���͂�����ID���L���ł��邩�ǂ������`�F�b�N���܂��B
            if (!validate.checkId(id)) {
                System.err.println(validate.getIdMsg());
            } else {
                
                // ���͂�����ID�����݃e�[�u���ɑ��݂��Ă��邩�ǂ������`�F�b�N���܂��B
                Student student = studentDao.findById(Integer.parseInt(id));
                if (student == null) {
                    System.err.println("�폜������ID�͌��݃e�[�u���ɑ��݂��Ă��܂���B");
                } else {
                    student.setId(Integer.parseInt(id));
                    
                    // �폜����O�ɂ�����x���[�U�[�Ɋm�F���܂��B
                    System.out.print("�{���ɍ폜���Ă���낵���ł���? �y�폜�������ꍇ -> true, �폜�������Ȃ��ꍇ -> false�z ");
                    boolean isSureDelete = scan.nextBoolean();
                    
                    // �폜SQL�����s���܂��B
                    int count = studentDao.delete(student, isSureDelete);
                    
                    //�@���ʏo��
                    System.out.println(count + "���폜���܂����B");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC�h���C�o�����[�h�ł��܂���ł����B");
        } catch (SQLException e) {
            System.err.println("SQL��O���������܂����B");
        } finally {
            DBUtils.close(conn);
        }
    }
}
