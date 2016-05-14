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
 * CRUD操作のメニューを表示して番号で選択します。
 * @author bangoku
 * @date 2016/05/14
 */
public class CRUD_Menu {

    /** コネクション */
    private static Connection conn = null;
    
    /** IDで検索するための選択する番号 */
    private static final int SELECT_BY_ID = 1;
    
    /** 全件検索するための選択する番号 */
    private static final int SELECT_ALL = 2;
    
    /**
     * メイン
     * @param args　メインオプション
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
                System.err.println("【エラー】 入力させた数字は正しくありません。\n");
                showMenuFlag = true;
                scan.nextLine();
            }
        } while (showMenuFlag == true);
        scan.close();
    }
    
    /**
     * 各CRUD操作のメニュー
     */
    public static void showMenu() {
        System.out.println("--------CRUD操作メニュー-----------");
        System.out.println("1. 一覧");
        System.out.println("2. 登録");
        System.out.println("3. 更新");
        System.out.println("4. 削除");
        System.out.println("5. 終了");
        System.out.print("[1~5]のいずれかの数字を入力してから操作してください: ");
    }
    
    /**
     * データ検索方法のメニュー
     */
    public static void subMenuOfFind() {
        System.out.println("検索システムを開始しました。");
        System.out.println("1. IDで検索");
        System.out.println("2. 全件検索");
        System.out.print("どちらのほうが検索したいですか。");
    }
    
    /**
     * データ検索
     */
    public static void find() {
        // 検索のサブメニューを表示します。
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
                System.err.println("【エラー】 入力させた数字は正しくありません。\n");
            }
        } catch (InputMismatchException e) {
            System.err.println("【エラー】 入力させた数字が正しくありません。");
        }
    }
    
    /**
     * IDでデータを検索します。
     */
    public static void findById() {
        Scanner scan = new Scanner(System.in);
        Validate validate = new Validate();
        
        try {
            // DB接続
            conn = DBUtils.getConnection();
            
            // StudentDao生成
            StudentDao studentDao = new StudentDao(conn);
            
            // ID入力
            System.out.print("IDを入力してください： ");
            String id = scan.nextLine();
            
            // 入力させたIDが有効であるかどうかをチェックします
            if (!validate.checkId(id)) {
                System.err.println(validate.getIdMsg());
            } else {
                // 入力させたIDで検索します。
                Student student = studentDao.findById(Integer.parseInt(id));
                
                // 結果出力
                if (student != null) {
                    System.out.println("-----------------------");
                    System.out.println(student.toString());
                    System.out.println("-----------------------");
                } else {
                    System.out.println("該当のデータがありませんでした。");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバがロードできませんでした。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL例外が発生しました。");
            e.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }
    }
    
    /**
     * ソート順を指定して全件検索します。
     */
    public static void findAll() {
        Scanner scan = new Scanner(System.in);
        Validate validate = new Validate();
        
        try {
            // DB接続
            conn = DBUtils.getConnection();
            
            // StudentDao生成
            StudentDao studentDao = new StudentDao(conn);
            
            // キーボードからカラム名を入力
            System.out.print("カラム名を入力してください: ");
            String columnName = scan.nextLine();
            
            // 入力させたカラム名はstudentテーブルに存在しているかどうかをチェックします。
            if (!validate.checkColumnName(columnName)) {
                System.err.println(validate.getColumnNameMsg());
            } else {
                
                // キーボードからどのソート順をしたいかを入力
                System.out.print("指定したカラムは降順(false)か昇順(true)かを入力してください： ");
                Boolean sortFlag = scan.nextBoolean();
                
                //　指定したカラム名とソート順で全件検索
                List<Student> list = studentDao.findAllSort(columnName, sortFlag);
                
                // 結果表示
                for (Student student : list) {
                    System.out.println("------------------------");
                    System.out.println(student.toString());
                    System.out.println("------------------------");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバがロードできませんでした。");
        } catch (SQLException e) {
            System.out.println("SQL例外が発生しました。");
        } finally {
            DBUtils.close(conn);
        }
    }
    
    /**
     * データを登録します。
     */
    public static void insert() {
        Scanner scan = new Scanner(System.in);
        Validate validate = new Validate();
        try {
            // DB接続
            conn = DBUtils.getConnection();
            
            // StudentDao生成
            StudentDao studentDao = new StudentDao(conn);
            
            // Student生成
            Student student = new Student();
            
            // ID入力
            System.out.print("IDを入力してください： ");
            String id = scan.nextLine();
            
            //入力させたIDが有効であるかどうかをチェックします。
            if (!validate.checkId(id)) {
                System.err.println(validate.getIdMsg());
            } else {
                student.setId(Integer.parseInt(id));
                
                // 名前入力
                System.out.print("名前を入力してください: ");
                String name = scan.nextLine();
                
                // 入力させた名前が有効であるかどうかをチェックします。
                if (!validate.checkName(name)) {
                    System.err.println(validate.getNameMsg());
                } else {
                    student.setName(name);
                    
                    // 生年月日入力
                    System.out.print("生年月日を入力してください: ");
                    String bday = scan.nextLine();
                    
                    // 入力させた生年月日が有効であるかどうかをチェックします。
                    if (!validate.checkBirthday(bday)) {
                        System.err.println(validate.getBirthdayMsg());
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        java.util.Date birthday = sdf.parse(bday);
                        student.setBirthday(new java.sql.Date(birthday.getTime()));
                        
                        // クラス名入力
                        System.out.print("クラス名を入力してください: ");
                        String cls = scan.nextLine();
                        
                        // 入力させたクラス名が有効であるかどうかをチェックします。
                        if (!validate.checkClassName(cls)) {
                            System.err.println(validate.getClassNameMsg());
                        } else {
                            student.setCls(cls);
                            
                            // insert SQLを実行します
                            int count = studentDao.insert(student);
                            
                            // 結果出力
                            System.out.println(count + "件登録しました。");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバがロードできませんでした。");
        } catch (SQLException e) {
            System.err.println("SQL例外が発生しました。");
        } catch (ParseException e) {
            System.err.println("誕生日のパースに失敗しました。");
        } finally {
            DBUtils.close(conn);
        }
    }
    
    /**
     * データを更新します。
     */
    public static void update() {
        Scanner scan = new Scanner(System.in);
        Validate validate = new Validate();
        
        try {
            // DB接続
            conn = DBUtils.getConnection();
            
            // StudentDao生成
            StudentDao studentDao = new StudentDao(conn);
            
            // Student生成
            Student student = new Student();
            
            System.out.println("更新システムを開始しました。");
            
            // ID入力
            System.out.print("IDを入力してください: ");
            String id = scan.nextLine();
            
            // 入力させたIDが有効であるかどうかをチェックします。
            if (!validate.checkId(id)) {
                System.err.println(validate.getIdMsg());
            } else {
            
                // 入力させたIDに対して現在DBに存在しているかどうかをチェックします。
                student = studentDao.findById(Integer.parseInt(id));
                if (student == null) {
                    System.err.println("入力させたIDは現在テーブルに存在していません。");
                } else {
                    student.setId(Integer.parseInt(id));
                    
                    // クラス名入力
                    System.out.print("更新したいクラス名を入力してください: ");
                    String cls = scan.nextLine();
                    
                    // 入力させたクラス名が有効であるかどうかをチェックします。
                    if (!validate.checkClassName(cls)) {
                        System.err.println(validate.getClassNameMsg());
                    } else {
                        student.setCls(cls);
                        
                        // update SQLを実行します
                        int count = studentDao.update(student);
                        
                        //　結果出力
                        System.out.println(count + "件更新しました。");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバがロードできませんでした。");
        } catch (SQLException e) {
            System.err.println("SQL例外が発生しました。");
        } finally {
            DBUtils.close(conn);
        }
    }
    
    /**
     * データを削除します。
     */
    public static void delete() {
        Scanner scan = new Scanner(System.in);
        Validate validate = new Validate();
        
        try {
            // DB接続
            conn = DBUtils.getConnection();
            
            // StudentDao生成
            StudentDao studentDao = new StudentDao(conn);
            
            System.out.println("削除システムを開始しました。");
            
            // ID入力
            System.out.print("削除IDを入力してください: ");
            String id = scan.nextLine();
            
            // 入力させたIDが有効であるかどうかをチェックします。
            if (!validate.checkId(id)) {
                System.err.println(validate.getIdMsg());
            } else {
                
                // 入力させたIDが現在テーブルに存在しているかどうかをチェックします。
                Student student = studentDao.findById(Integer.parseInt(id));
                if (student == null) {
                    System.err.println("削除したいIDは現在テーブルに存在していません。");
                } else {
                    student.setId(Integer.parseInt(id));
                    
                    // 削除する前にもう一度ユーザーに確認します。
                    System.out.print("本当に削除してもよろしいですか? 【削除したい場合 -> true, 削除したくない場合 -> false】 ");
                    boolean isSureDelete = scan.nextBoolean();
                    
                    // 削除SQLを実行します。
                    int count = studentDao.delete(student, isSureDelete);
                    
                    //　結果出力
                    System.out.println(count + "件削除しました。");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバがロードできませんでした。");
        } catch (SQLException e) {
            System.err.println("SQL例外が発生しました。");
        } finally {
            DBUtils.close(conn);
        }
    }
}
