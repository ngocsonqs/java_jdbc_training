package jp.co.bangoku.jdbc.process;

import java.util.Scanner;

/**
 * DBに各CRUD操作をメニューでキーボードから番号を入力させてDB操作をします。
 * @author bangoku
 * @date 2016/05/12
 */
public class CRUDMenu_02 {
    /**
     * メインメソッド
     * @param args メインオプション
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        Boolean showMenuFlag = true;
        
        do {
            showMenu();
            int selectNum = scan.nextInt();
            scan.nextLine();
            switch (selectNum) {
                    case 1:
                        findById();
                        break;
                    case 2:
                        findAll();
                        break;
                    case 3:
                        insert();
                        break;
                    case 4:
                        update();
                        break;
                    case 5:
                        delete();
                        break;
                    case 6:
                    default:
                        exit();
                        showMenuFlag = false;
                        break;
            }
        } while (showMenuFlag == true);
        scan.close();
    }
    
    /**
     * DBに各CRUD操作のメニュー
     */
    public static void showMenu() {
        System.out.println("------CRUD操作メニュー--------");
        System.out.println("1. FIND BY ID");
        System.out.println("2. FIND ALL");
        System.out.println("3. INSERT DATA");
        System.out.println("4. UPDATE DATA");
        System.out.println("5. DELETE DATA");
        System.out.println("6. EXIT");
        System.out.print("[1～6]のいずれかの数字を入力してから操作してください: ");
    }
    
    /**
     * ID検索
     */
    public static void findById() {
        System.out.println("CRUDMenu.findById()");
    }
    
    /**
     * 全件検索
     */
    public static void findAll() {
        System.out.println("CRUDMenu.findAll()");
    }
    
    /**
     * データ挿入
     */
    public static void insert() {
        System.out.println("CRUDMenu.insert()");
    }
    
    /**
     * データ更新
     */
    public static void update() {
        System.out.println("CRUDMenu.update()");
    }
    
    /**
     * データ削除
     */
    public static void delete() {
        System.out.println("CRUDMenu.delete()");
    }
    
    /**
     * システム停止
     */
    public static void exit() {
        System.out.println("CRUDMenu.exit()");
    }
}
