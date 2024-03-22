package Google;

import java.util.*;

public class GoogleMain {
    public static void main(String[] args) {
        GoogleMember member = new GoogleMember();
        GoogleSQL sql = new GoogleSQL();

        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int menu = 0;

        boolean run1 = true;
        int menu1 = 0;

        sql.connect();

        while (run) {
            System.out.println("============================");
            System.out.println("[1]회원가입\t\t[2]회원목록");
            System.out.println("[3]로그인\t\t[4]종료");
            System.out.println("============================");
            System.out.print("선택 >> ");
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    System.out.println("회원정보를 입력해주세요");

                    System.out.print("아이디: ");
                    String gId = sc.next();

                    System.out.print("비밀번호: ");
                    String gPw = sc.next();

                    System.out.print("이메일: ");
                    String gEamil = sc.next();

                    System.out.print("이   름: ");
                    String gName = sc.next();

                    System.out.print("생년월일: ");
                    String gBirth = sc.next();

                    System.out.print("성   별: ");
                    String gGender = sc.next();

                    System.out.print("연락처: ");
                    String gPhone = sc.next();

                    member.setgId(gId);
                    member.setgPw(gPw);
                    member.setgEmail(gEamil);
                    member.setgName(gName);
                    member.setgBirth(gBirth);
                    member.setgGender(gGender);
                    member.setgPhone(gPhone);

                    sql.memberJoin(member);

                    break;
                case 2:
                    sql.memberList();
                    break;
                case 3:
                    System.out.println("아이디: ");
                    gId = sc.next();

                    System.out.println("비밀번호: ");
                    gPw = sc.next();
                    boolean login = sql.memberLogin(gId, gPw);

                    if (login) {
                        System.out.println("로그인 성공");
                        run1 = true;
                        while (run1) {
                            System.out.println("============================");
                            System.out.println("[1]내정보\t\t[2]정보수정");
                            System.out.println("[3]회원탈퇴\t\t[4]로그아웃");
                            System.out.println("============================");

                            System.out.print("선택 >> ");
                            menu1 = sc.nextInt();
                            switch (menu1) {
                                case 1:
                                    sql.myInfo(gId);
                                    break;
                                case 2:
                                    System.out.println("수정할 정보를 입력해주세요");

                                    System.out.print("비밀번호: ");
                                    gPw = sc.next();

                                    System.out.print("이메일: ");
                                    gEamil = sc.next();

                                    System.out.print("이   름: ");
                                    gName = sc.next();

                                    System.out.print("생년월일: ");
                                    gBirth = sc.next();

                                    System.out.print("성   별: ");
                                    gGender = sc.next();

                                    System.out.print("연락처: ");
                                    gPhone = sc.next();

                                    member.setgId(gId);
                                    member.setgPw(gPw);
                                    member.setgEmail(gEamil);
                                    member.setgName(gName);
                                    member.setgBirth(gBirth);
                                    member.setgGender(gGender);
                                    member.setgPhone(gPhone);

                                    sql.memberModify(member);
                                    break;
                                case 3:
                                    System.out.println("탈퇴를 위한 아이디&패스워드 입력");
                                    System.out.print("아이디: ");
                                    gId = sc.next();

                                    System.out.print("비밀번호: ");
                                    gPw = sc.next();
                                    boolean checked = sql.memberLogin(gId, gPw);

                                    if (checked) {
                                        System.out.println("회원정보가 일치합니다.");
                                        sql.myInfoDelete(gId, gPw);
                                        run1 = false;
                                        System.out.println("이전 메뉴로 돌아갑니다");
                                    } else {
                                        System.out.println("회원정보가 일치하지 않음");
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

                    } else {
                        System.out.println("로그인 실패");
                    }
                    break;

                case 4:
                    run = false;
                    sql.conClose();
                    System.out.println("시스템 종료 중...");
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
                    break;
            }
        }
    }
}
