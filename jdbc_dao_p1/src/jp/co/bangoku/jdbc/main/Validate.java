package jp.co.bangoku.jdbc.main;

import org.apache.commons.lang3.ArrayUtils;

/**
 * �L�[�{�[�h������͂����l���`�F�b�N���܂��B
 * @author bangoku
 * @date 2016/05/12
 */
public class Validate {

    /** ID���̓p�^�[�� */
    private static final String ID_PATERN = "[\\d]{1,10}";
    
    /** ���O���̓p�^�[�� */
    private static final String NAME_PATTERN = "[\\p{L}]{1,128}";
    
    /** ���N�������̓p�^�[�� */
    private static final String BIRTHDAY_PATTERN = "[\\d]{8}";
    
    /** �N���X�����̓p�^�[�� */
    private static final String CLASS_NAME_PATTERN = "[\\p{L}]{1,8}";
    
    /** ���݂�DB�ɑ��݂��Ă���J������ */
    private static final String[] COLUMN_NAME = {"id", "name", "birthday", "class_name"};
    
    /** ID���b�Z�[�W */
    private String idMsg;
    
    /** ���O�̃��b�Z�[�W */
    private String nameMsg;
    
    /** ���N�����̃��b�Z�[�W */
    private String birthdayMsg;
    
    /** �N���X���̃��b�Z�[�W */
    private String classNameMsg;
    
    /** �J�������̃��b�Z�[�W */
    private String columnNameMsg;
    
    /**
     * ID���b�Z�[�W���擾���܂��B
     * @return�@ID���b�Z�[�W
     */
    public String getIdMsg() {
        return idMsg;
    }
 
    /**
     * ���͂�����ID�̃G���[���b�Z�[�W��ݒ�
     * @param idMsg ID���b�Z�[�W
     */
    public void setIdMsg(String idMsg) {
        this.idMsg = idMsg;
    }
 
    /**
     * ���O�̃G���[���b�Z�[�W���擾���܂��B
     * @return�@���O�̃G���[���b�Z�[�W
     */
    public String getNameMsg() {
        return nameMsg;
    }
 
    /**
     * ���͂��������O�̃G���[���b�Z�[�W��ݒ肵�܂��B
     * @param nameMsg�@���O�̃��b�Z�[�W
     */
    public void setNameMsg(String nameMsg) {
        this.nameMsg = nameMsg;
    }
 
    /**
     * ���N�����̃G���[���b�Z�[�W���擾���܂��B
     * @return�@���N�����̃G���[���b�Z�[�W
     */
    public String getBirthdayMsg() {
        return birthdayMsg;
    }
 
    /**
     * ���͂��������N�����̃G���[���b�Z�[�W��ݒ肵�܂��B
     * @param birthdayMsg�@���N�������b�Z�[�W
     */
    public void setBirthdayMsg(String birthdayMsg) {
        this.birthdayMsg = birthdayMsg;
    }
 
    /**
     *�@�N���X���̃G���[���b�Z�[�W���擾���܂��B
     * @return�@�N���X���̃G���[���b�Z�[�W
     */
    public String getClassNameMsg() {
        return classNameMsg;
    }
 
    /**
     * ���͂������N���X���̃G���[���b�Z�[�W��ݒ肵�܂��B
     * @param classNameMsg�@�N���X�����b�Z�[�W
     */
    public void setClassNameMsg(String classNameMsg) {
        this.classNameMsg = classNameMsg;
    }
    
    /**
     * �J�������̃G���[���b�Z�[�W���擾���܂��B
     * @return�@�J�������̃��b�Z�[�W
     */
    public String getColumnNameMsg() {
        return columnNameMsg;
    }
 
    /**
     * ���͂������J��������DB�ɑ��݂��Ȃ��G���[���b�Z�[�W��ݒ肵�܂��B
     * @param columnNameMsg �J�������̃��b�Z�[�W
     */
    public void setColumnNameMsg(String columnNameMsg) {
        this.columnNameMsg = columnNameMsg;
    }
 
    /**
     * ���[�U�[������͂���ID���`�F�b�N���܂��B
     * @param idValue ID
     * @return true/false
     */
    public boolean checkId(String idValue) {
        if (!idValue.isEmpty()) {
            if (!idValue.matches(ID_PATERN)) {
                this.setIdMsg("�y�G���[�zID��10���ȓ��̔��p�p���ōē��͂��Ă��������B");
                return false;
            }
        } else {
            System.err.println("�y�G���[�zID�͂܂����͂��Ă��܂���B");
            return false;
        }
        return true;
    }
    
    /**
     * ���[�U�[������͂������O���`�F�b�N���܂��B
     * @param name ���O
     * @return true/false
     */
    public boolean checkName(String name) {
        if (!name.isEmpty()) {
            if (!name.matches(NAME_PATTERN)) {
                this.setNameMsg("�y�G���[�z���O��128���ȓ��̔��p�����ōē��͂��Ă��������B");
                return false;
            }
        } else {
            System.err.println("�y�G���[�z���O�͂܂����͂��Ă��܂���B");
            return false;
        }
        return true;
    }
    
 
    /**
     * ���[�U�[������͂������N�������`�F�b�N���܂��B
     * @param birthday�@���N����
     * @return true/false
     */
    public boolean checkBirthday(String birthday) {
        if (!birthday.isEmpty()) {
            if (!birthday.matches(BIRTHDAY_PATTERN)) {
                this.setBirthdayMsg(
                        "�y�G���[�z�@���N�����͔��p�p������yyyyMMdd�Ƃ����`���ōē��͂��Ă��������B");
                return false;
            }
        } else {
            System.err.println("�y�G���[�z���N�����͂܂����͂��Ă��܂���B");
            return false;
        }
        return true;
    }
    
    /**
     * ���[�U�[������͂����N���X�����`�F�b�N���܂��B
     * @param className �N���X��
     * @return true/false
     */
    public boolean checkClassName(String className) {
        if (!className.isEmpty()) {
            if ( !className.matches(CLASS_NAME_PATTERN)) {
                this.setClassNameMsg("�y�G���[�z �N���X����8���ȓ��̕����œ��͂��Ă��������B");
                return false;
            }
        } else {
            System.err.println("�y�G���[�z�@�N���X���͂܂����͂��Ă��܂���B");
            return false;
        }
        return true;
    }
    
    /**
     * ���[�U�[������͂����J��������DB�ɑ��݂��Ă��邩�ǂ����`�F�b�N���܂��B
     * @param columnName �N������
     * @return true: ���݂��Ă��܂��@�E�@false:���݂��Ă��܂���B
     */
    public boolean checkColumnName(String columnName) {
        if (!columnName.isEmpty()) {
            
            // ���͂������N�������͒�`����COLUMN_NAME�z��ɑ��݂��邩�ǂ������`�F�b�N���܂��B
            if (!ArrayUtils.contains(COLUMN_NAME, columnName)) {
                this.setColumnNameMsg("�y�G���[�z�@���͂������J�����������݂��܂���B");
                return false;
            }
        } else {
            System.err.println("�y�G���[�z �J�������͂܂����͂��Ă��܂���B");
        }
        return true;
    }
}