package NAVER;

import java.util.*;

public class NaverMain {
    public static void main(String[] args) {

        // NaverMember 객체
        NaverMember member = new NaverMember();
        // NaverSQL 객체
        NaverSQL sql = new NaverSQL();

        // 프로그램 실행에 필요한 객체, 변수
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int menu = 0;

        boolean run1 = true;
        int menu1 = 0;

        // 프로그램을 처음 실행할 경우 DB접속
        sql.connect();

        while(run){
            System.out.println("============================");
            System.out.println("[1]회원가입\t\t[2]회원목록");
            System.out.println("[3]로그인\t\t[4]종료");
            System.out.println("============================");

            System.out.print("선택 >> ");
            menu = sc.nextInt();
            switch (menu){
                case 1:
                    System.out.println("회원정보를 입력해주세요");

                    System.out.print("아이디: ");
                    String nId = sc.next();

                    System.out.print("비밀번호: ");
                    String nPw = sc.next();

                    System.out.print("이메일: ");
                    String nEamil = sc.next();

                    System.out.print("이   름: ");
                    String nName = sc.next();

                    System.out.print("생년월일: ");
                    String nBirth = sc.next();

                    System.out.print("성   별: ");
                    String nGender = sc.next();

                    System.out.print("연락처: ");
                    String nPhone = sc.next();

                    // 입력받은 7가지 정보를 member객체에 담는다
                    member.setnId(nId);
                    member.setnPw(nPw);
                    member.setnEmail(nEamil);
                    member.setnName(nName);
                    member.setnBirth(nBirth);
                    member.setnGender(nGender);
                    member.setnPhone(nPhone);

                    // NaverSQL클래스에 memberJoin()메소드를 만들고
                    // 정보를 담은 member객체를 매개변수로 전달한다.
                    sql.memberJoin(member);
                    break;

                case 2:
                    sql.memberList();
                    break;
                case 3:
                    System.out.print("아이디: ");
                    nId = sc.next();
                    System.out.print("비밀번호: ");
                    nPw = sc.next();

                    boolean login = sql.memberLogin(nId, nPw);
                    
                    if(login){
                        System.out.println("로그인 성공");
                        run1 = true;    // 작성하지 않을 경우 로그인 성공하더라도 메뉴가 나오지 않는다.
                        while(run1) {
                            System.out.println("============================");
                            System.out.println("[1]내정보\t\t[2]정보수정");
                            System.out.println("[3]회원탈퇴\t\t[4]로그아웃");
                            System.out.println("============================");

                            System.out.print("선택 >> ");
                            menu1 = sc.nextInt();
                            switch (menu1){
                                case 1:
                                    // nId를 이용해서 내 정보보기
                                    sql.myInfo(nId);
                                    break;
                                case 2:
                                    System.out.println("수정할 정보를 입력해주세요");
                                    
                                    System.out.print("비밀번호: ");
                                    nPw = sc.next();

                                    System.out.print("이메일: ");
                                    nEamil = sc.next();

                                    System.out.print("이   름: ");
                                    nName = sc.next();

                                    System.out.print("생년월일: ");
                                    nBirth = sc.next();

                                    System.out.print("성   별: ");
                                    nGender = sc.next();

                                    System.out.print("연락처: ");
                                    nPhone = sc.next();

                                    // 입력받은 7가지 정보를 member객체에 담는다
                                    member.setnId(nId);
                                    member.setnPw(nPw);
                                    member.setnEmail(nEamil);
                                    member.setnName(nName);
                                    member.setnBirth(nBirth);
                                    member.setnGender(nGender);
                                    member.setnPhone(nPhone);

                                    sql.memberModify(member);
                                    break;
                                case 3:
                                    System.out.println("정말 삭제하시겠습니까? (y/n)");
                                    String checkDelete = sc.next();

                                    switch (checkDelete){
                                        case "y":
                                            sql.myInfoDelete(nId, nPw);
                                            run1 = false;
                                            System.out.println("이전 메뉴로 돌아갑니다");
                                            break;
                                        case "n":
                                            System.out.println("삭제를 취소합니다");
                                            break;
                                        default:
                                            System.out.println("y와 n중에 입력");
                                            break;
                                    }
                                    
                                    break;
                                case 4:
                                    run1 = false;
                                    System.out.println("이전 메뉴로 돌아갑니다");
                                    break;
                                default:
                                    System.out.println("잘못입력하셨습니다");
                                    break;
                            }
                        }

                    } else{
                        System.out.println("로그인 실패");
                    }
                    break;

                case 4:
                    run = false;
                    sql.conClose();
                    System.out.println("시스템 종료...");
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
                    break;
            }
        }

    }
}
