package BANK;

import java.sql.SQLOutput;
import java.util.*;

public class BankMain {
    public static void main(String[] args) {
        BankClient client = new BankClient();
        BankSQL sql = new BankSQL();

        sql.Connent(); // DB접속

        Scanner sc = new Scanner(System.in);
        boolean run = true;
        int menu = 0;

        System.out.println("IC8은행에 오신 것을 환영합니다");
        System.out.println("무엇을 도와드릴까요?\n");

        while (run) {
            System.out.println("===========================================");
            System.out.println("[1]생성 \t\t [2]입급 \t\t [3]출금");
            System.out.println("[4]조회 \t\t [5]송금 \t\t [6]종료");
            System.out.println("===========================================");

            System.out.print("선택 >> ");
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    // 고객번호(자동)
                    int cNum = sql.clientNumber() + 1;

                    // 고객이름(작성)
                    System.out.print("고객이름: ");
                    String cName = sc.next();

                    // 계좌번호(자동)
                    String cAccount = sql.accountNumber();

                    // 잔액
                    int balance = 0;

                    client.setcNum(cNum);
                    client.setcName(cName);
                    client.setcAccount(cAccount);
                    client.setBalance(balance);

                    sql.joinClient(client);

                    break;

                case 2:
                    // (1) 입금할 계좌번호 입력
                    System.out.print("입금 계좌번호 입력: ");
                    cAccount = sc.next();

                    // (2) 계좌번호 유무
                    boolean checked = sql.checkAccount(cAccount);

                    // (3) 입금 진행
                    if (checked) {
                        System.out.print("입금할 금액: ");
                        int amount = sc.nextInt();

                        sql.deposit(cAccount, amount);
                        System.out.println("입금 성공");
                    } else {
                        System.out.println("계좌가 존재하지 않습니다.");
                    }

                    break;

                case 3:
                    System.out.print("출금 계좌번호 입력: ");
                    cAccount = sc.next();

                    // (2) 계좌번호 유무
                    checked = sql.checkAccount(cAccount);

                    // (3) 출금 진행
                    if (checked) {
                        System.out.print("출금할 금액: ");
                        int amount = sc.nextInt();

                        balance = sql.checkBalance(cAccount);
                        if (balance >= amount) {
                            sql.withdraw(cAccount, amount);
                            System.out.println("출금성공");
                        } else {
                            System.out.println("잔액 부족");
                            System.out.println("잔액이 " + (amount - balance) + "부족 합니다");
                        }
                    } else {
                        System.out.println("계좌가 존재하지 않습니다.");
                    }
                    break;

                case 4:
                    System.out.print("잔액 확인할 계좌번호: ");
                    cAccount = sc.next();
                    checked = sql.checkAccount(cAccount);
                    if (checked) {
                        balance = sql.checkBalance(cAccount);
                        System.out.println("잔액: " + balance + "원");
                    } else {
                        System.out.println("계좌가 존재 하지 않습니다");
                    }

                    break;

                case 5:
                    // 입력할 정보: 보내는 사람 계좌, 받는 사람 계좌, 송금액
                    System.out.print("본인 계좌번호 입력: ");
                    String sAccount = sc.next();

                    // 보내는 사람 계좌 유무 확인
                    if (sql.checkAccount(sAccount)) {
                        System.out.print("송금할 계좌번호 입력: ");
                        String rAccount = sc.next();
                        // 받는 사람 계좌 유무 확인
                        if (sql.checkAccount(rAccount)) {
                            // 보내는 사람의 잔액이 송금액보다 많은지 확인
                            System.out.println("송금할 금액: ");
                            int amount = sc.nextInt();

                            balance = sql.checkBalance(sAccount);

                            if (balance >= amount) {
                                // 보내는 사람의 잔액이 송금액보다 많은지 확인
                                sql.withdraw(sAccount, amount);

                                sql.deposit(rAccount, amount);

                                System.out.println("송금 성공");
                            }
                        } else {
                            System.out.println("계좌가 존재하지 않습니다");
                        }
                    } else {
                        System.out.println("계좌가 존재하지 않습니다");
                    }

                    break;

                case 6:
                    System.out.println("이용해 주셔서 감사합니다.");
                    sql.conClose();
                    run = false;
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
                    break;
            }

        }
        sc.close();


    }
}
