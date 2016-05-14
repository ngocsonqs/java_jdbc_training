package jp.co.bangoku.jdbc.entity;

import java.sql.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * StudentテーブルのValueObjectです。
 * @author bangoku
 * @date 2016/05/12
 */
public class Student {
    
    /** IDカラム */
    private int id;
    
    /** 名前カラム */
    private String name;
    
    /** 生年月日 */
    private Date birthday;
    
    /** クラス名 */
    private String cls;
    
    /**
     * IDの設定
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * IDの取得
     * @return ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * 名前の設定
     * @param name 名前
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 名前の取得
     * @return 名前
     */
    public String getName() {
        return name;
    }
    
    /**
     * 生年月日の設定
     * @param birthday 生年月日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    /**
     * 生年月日の取得
     * @return 生年月日
     */
    public Date getBirthday() {
        return birthday;
    }
    
    /**
     * クラス名の設定
     * @param cls クラス名
     */
    public void setCls(String cls) {
        this.cls = cls;
    }
    
    /**
     * クラス名の設定
     * @return　クラス名
     */
    public String getCls() {
        return cls;
    }
    
    /**
     *  ComonsLangのToStringBuilderの形で結果出力
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}