package jp.co.bangoku.jdbc.process;

import java.util.Scanner;

/**
 * DB�ɊeCRUD��������j���[�ŃL�[�{�[�h����ԍ�����͂�����DB��������܂��B
 * @author bangoku
 * @date 2016/05/12
 */
public class CRUDMenu_02 {
    /**
     * ���C�����\�b�h
     * @param args ���C���I�v�V����
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
     * DB�ɊeCRUD����̃��j���[
     */
    public static void showMenu() {
        System.out.println("------CRUD���상�j���[--------");
        System.out.println("1. FIND BY ID");
        System.out.println("2. FIND ALL");
        System.out.println("3. INSERT DATA");
        System.out.println("4. UPDATE DATA");
        System.out.println("5. DELETE DATA");
        System.out.println("6. EXIT");
        System.out.print("[1�`6]�̂����ꂩ�̐�������͂��Ă��瑀�삵�Ă�������: ");
    }
    
    /**
     * ID����
     */
    public static void findById() {
        System.out.println("CRUDMenu.findById()");
    }
    
    /**
     * �S������
     */
    public static void findAll() {
        System.out.println("CRUDMenu.findAll()");
    }
    
    /**
     * �f�[�^�}��
     */
    public static void insert() {
        System.out.println("CRUDMenu.insert()");
    }
    
    /**
     * �f�[�^�X�V
     */
    public static void update() {
        System.out.println("CRUDMenu.update()");
    }
    
    /**
     * �f�[�^�폜
     */
    public static void delete() {
        System.out.println("CRUDMenu.delete()");
    }
    
    /**
     * �V�X�e����~
     */
    public static void exit() {
        System.out.println("CRUDMenu.exit()");
    }
}
