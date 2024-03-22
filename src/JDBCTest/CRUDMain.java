package JDBCTest;

import java.util.Scanner;

public class CRUDMain {

    public static void main(String[] args) {

        // CRUD 객체 생성
        CRUD crud = new CRUD();

//        crud.connect();

//        crud.insert();
//        crud.update();
//        crud.delete();

//        crud.select();

//        crud.selectEMP();


//        crud.conClose();

        // crud.selectEMP();
        // EMP 테이블 조회

        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int menu = 0;

        while(run){
            System.out.println("========================================");
            System.out.println("[1] DB접속\t\t[2]insert\t\t[3]select");
            System.out.println("[4] update\t\t[5]delete\t\t[6]close");
            System.out.println("========================================");

            System.out.print("선택 >> ");
            menu = sc.nextInt();

            switch(menu){
                case 1:
                    crud.connect();
                    break;
                case 2:
                    crud.insert();
                    break;
                case 3:
                    crud.select();
                    break;
                case 4:
                    crud.update();
                    break;
                case 5:
                    crud.delete();
                    break;
                case 6:
                    crud.conClose();
                    break;
                default:
                    System.out.println("다시 입력해주세요");
                    break;
            }
        }

       }

}
