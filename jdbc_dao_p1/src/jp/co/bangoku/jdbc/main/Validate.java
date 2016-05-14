package jp.co.bangoku.jdbc.main;

import org.apache.commons.lang3.ArrayUtils;

/**
 * キーボードから入力した値をチェックします。
 * @author bangoku
 * @date 2016/05/12
 */
public class Validate {

    /** ID入力パターン */
    private static final String ID_PATERN = "[\\d]{1,10}";
    
    /** 名前入力パターン */
    private static final String NAME_PATTERN = "[\\p{L}]{1,128}";
    
    /** 生年月日入力パターン */
    private static final String BIRTHDAY_PATTERN = "[\\d]{8}";
    
    /** クラス名入力パターン */
    private static final String CLASS_NAME_PATTERN = "[\\p{L}]{1,8}";
    
    /** 現在のDBに存在しているカラム名 */
    private static final String[] COLUMN_NAME = {"id", "name", "birthday", "class_name"};
    
    /** IDメッセージ */
    private String idMsg;
    
    /** 名前のメッセージ */
    private String nameMsg;
    
    /** 生年月日のメッセージ */
    private String birthdayMsg;
    
    /** クラス名のメッセージ */
    private String classNameMsg;
    
    /** カラム名のメッセージ */
    private String columnNameMsg;
    
    /**
     * IDメッセージを取得します。
     * @return　IDメッセージ
     */
    public String getIdMsg() {
        return idMsg;
    }
 
    /**
     * 入力させたIDのエラーメッセージを設定
     * @param idMsg IDメッセージ
     */
    public void setIdMsg(String idMsg) {
        this.idMsg = idMsg;
    }
 
    /**
     * 名前のエラーメッセージを取得します。
     * @return　名前のエラーメッセージ
     */
    public String getNameMsg() {
        return nameMsg;
    }
 
    /**
     * 入力させた名前のエラーメッセージを設定します。
     * @param nameMsg　名前のメッセージ
     */
    public void setNameMsg(String nameMsg) {
        this.nameMsg = nameMsg;
    }
 
    /**
     * 生年月日のエラーメッセージを取得します。
     * @return　生年月日のエラーメッセージ
     */
    public String getBirthdayMsg() {
        return birthdayMsg;
    }
 
    /**
     * 入力させた生年月日のエラーメッセージを設定します。
     * @param birthdayMsg　生年月日メッセージ
     */
    public void setBirthdayMsg(String birthdayMsg) {
        this.birthdayMsg = birthdayMsg;
    }
 
    /**
     *　クラス名のエラーメッセージを取得します。
     * @return　クラス名のエラーメッセージ
     */
    public String getClassNameMsg() {
        return classNameMsg;
    }
 
    /**
     * 入力させたクラス名のエラーメッセージを設定します。
     * @param classNameMsg　クラス名メッセージ
     */
    public void setClassNameMsg(String classNameMsg) {
        this.classNameMsg = classNameMsg;
    }
    
    /**
     * カラム名のエラーメッセージを取得します。
     * @return　カラム名のメッセージ
     */
    public String getColumnNameMsg() {
        return columnNameMsg;
    }
 
    /**
     * 入力させたカラム名はDBに存在しないエラーメッセージを設定します。
     * @param columnNameMsg カラム名のメッセージ
     */
    public void setColumnNameMsg(String columnNameMsg) {
        this.columnNameMsg = columnNameMsg;
    }
 
    /**
     * ユーザーから入力したIDをチェックします。
     * @param idValue ID
     * @return true/false
     */
    public boolean checkId(String idValue) {
        if (!idValue.isEmpty()) {
            if (!idValue.matches(ID_PATERN)) {
                this.setIdMsg("【エラー】IDは10桁以内の半角英数で再入力してください。");
                return false;
            }
        } else {
            System.err.println("【エラー】IDはまだ入力していません。");
            return false;
        }
        return true;
    }
    
    /**
     * ユーザーから入力した名前をチェックします。
     * @param name 名前
     * @return true/false
     */
    public boolean checkName(String name) {
        if (!name.isEmpty()) {
            if (!name.matches(NAME_PATTERN)) {
                this.setNameMsg("【エラー】名前は128桁以内の半角文字で再入力してください。");
                return false;
            }
        } else {
            System.err.println("【エラー】名前はまだ入力していません。");
            return false;
        }
        return true;
    }
    
 
    /**
     * ユーザーから入力した生年月日をチェックします。
     * @param birthday　生年月日
     * @return true/false
     */
    public boolean checkBirthday(String birthday) {
        if (!birthday.isEmpty()) {
            if (!birthday.matches(BIRTHDAY_PATTERN)) {
                this.setBirthdayMsg(
                        "【エラー】　生年月日は半角英数字でyyyyMMddという形式で再入力してください。");
                return false;
            }
        } else {
            System.err.println("【エラー】生年月日はまだ入力していません。");
            return false;
        }
        return true;
    }
    
    /**
     * ユーザーから入力したクラス名をチェックします。
     * @param className クラス名
     * @return true/false
     */
    public boolean checkClassName(String className) {
        if (!className.isEmpty()) {
            if ( !className.matches(CLASS_NAME_PATTERN)) {
                this.setClassNameMsg("【エラー】 クラス名は8桁以内の文字で入力してください。");
                return false;
            }
        } else {
            System.err.println("【エラー】　クラス名はまだ入力していません。");
            return false;
        }
        return true;
    }
    
    /**
     * ユーザーから入力したカラム名はDBに存在しているかどうかチェックします。
     * @param columnName クラム名
     * @return true: 存在しています　・　false:存在していません。
     */
    public boolean checkColumnName(String columnName) {
        if (!columnName.isEmpty()) {
            
            // 入力させたクラム名は定義したCOLUMN_NAME配列に存在するかどうかをチェックします。
            if (!ArrayUtils.contains(COLUMN_NAME, columnName)) {
                this.setColumnNameMsg("【エラー】　入力させたカラム名が存在しません。");
                return false;
            }
        } else {
            System.err.println("【エラー】 カラム名はまだ入力していません。");
        }
        return true;
    }
}