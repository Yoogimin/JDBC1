package JPSTU;

import java.util.Scanner;

public class JPMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int menu = 0;

        // JPMember 객체
        JPMember member = new JPMember();

        //JPSQL 객체
        JPSQL sql = new JPSQL();

        while(run){
            System.out.println("==========================================");
            System.out.println("[1]DB접속\t\t[2]접속해제\t\t[3]학생등록\t\t[4]학생목록");
            System.out.println("[5]학생수정\t\t[6]학생삭제\t\t[7]종   료");
            System.out.println("==========================================");

            System.out.print("선택 >> ");
            menu = sc.nextInt();

            switch(menu){
                case 1:
                    sql. connect();
                    break;
                case 2:
                    sql.conClose();
                    break;
                case 3:
                    System.out.println("학생정보를 입력하시오");

                    System.out.print("학생번호: ");
                    int jNum = sc.nextInt();

                    System.out.print("비밀번호: ");
                    String jPw = sc.next();

                    System.out.print("이   름: ");
                    String jName = sc.next();

                    System.out.print("나   이: ");
                    String jAge = sc.next();

                    System.out.print("성   별: ");
                    String jGender = sc.next();

                    System.out.print("E-mail: ");
                    String jEmail = sc.next();

                    System.out.print("전화번호: ");
                    String jPhone = sc.next();

                    member.setjNum(jNum);
                    member.setjPw(jPw);
                    member.setjName(jName);
                    member.setjAge(jAge);
                    member.setjGender(jGender);
                    member.setjEmail(jEmail);
                    member.setjPhone(jPhone);

                    sql.memberJoin(member);
                    break;
                case 4:
                    sql.memberlist();
                    break;
                case 5:
                    System.out.print("수정할 학생번호: ");
                    jNum = sc.nextInt();

                    System.out.print("비밀번호: ");
                    jPw = sc.next(); // 변경전 비밀번호

                    // 학생번호와 비밀번호가 일치하는지 확인
                    boolean checked = sql.idCheck(jNum, jPw);

                    if(checked){
                        System.out.println("학생정보가 일치합니다.");
                        System.out.println("수정할 회원정보를 입력해 주세요");
                        
                        System.out.println();
                        System.out.print("비밀번호: "); // 변경후 비밀번호
                        jPw = sc.next();

                        System.out.print("이   름: ");
                        jName = sc.next();

                        System.out.print("나   이: ");
                        jAge = sc.next();

                        System.out.print("성   별: ");
                        jGender = sc.next();

                        System.out.print("E-mail: ");
                        jEmail = sc.next();

                        System.out.print("전화번호: ");
                        jPhone = sc.next();

                        member.setjNum(jNum);
                        member.setjPw(jPw);
                        member.setjName(jName);
                        member.setjAge(jAge);
                        member.setjGender(jGender);
                        member.setjEmail(jEmail);
                        member.setjPhone(jPhone);

                        sql.memberModify(member);

                    } else {
                        System.out.println("학생정보가 일치하지 않습니다");
                    }
                    break;
                case 6:
                    System.out.print("수정할 학생번호: ");
                    jNum = sc.nextInt();

                    System.out.print("비밀번호: ");
                    jPw = sc.next();
                    boolean checked00 = sql.idCheck(jNum, jPw);

                    if(checked00) {
                        System.out.println("학생정보가 일치합니다.");
                        sql.memberDelete(jNum);
                    }else{
                        System.out.println("학생정보가 일치하지 않음");
                    }

                    break;
                case 7:
                    run = false;
                    System.out.println("시스템 종료");
                    break;
                default:
                    System.out.println("다시 입력하시오");
                    break;
            }
        }
    }
}
