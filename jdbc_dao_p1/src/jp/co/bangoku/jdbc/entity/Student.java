package jp.co.bangoku.jdbc.entity;

import java.sql.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Student�e�[�u����ValueObject�ł��B
 * @author bangoku
 * @date 2016/05/12
 */
public class Student {
    
    /** ID�J���� */
    private int id;
    
    /** ���O�J���� */
    private String name;
    
    /** ���N���� */
    private Date birthday;
    
    /** �N���X�� */
    private String cls;
    
    /**
     * ID�̐ݒ�
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * ID�̎擾
     * @return ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * ���O�̐ݒ�
     * @param name ���O
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * ���O�̎擾
     * @return ���O
     */
    public String getName() {
        return name;
    }
    
    /**
     * ���N�����̐ݒ�
     * @param birthday ���N����
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    /**
     * ���N�����̎擾
     * @return ���N����
     */
    public Date getBirthday() {
        return birthday;
    }
    
    /**
     * �N���X���̐ݒ�
     * @param cls �N���X��
     */
    public void setCls(String cls) {
        this.cls = cls;
    }
    
    /**
     * �N���X���̐ݒ�
     * @return�@�N���X��
     */
    public String getCls() {
        return cls;
    }
    
    /**
     *  ComonsLang��ToStringBuilder�̌`�Ō��ʏo��
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}