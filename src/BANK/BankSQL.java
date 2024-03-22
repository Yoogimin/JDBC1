package BANK;

import JDBCTest.DBConnection;

import java.sql.*;

public class BankSQL {

    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public void Connent(){ con = DBC.DBConnect(); }

    public void conClose(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 고객번호 생성 메소드
    public int clientNumber() {
        int cNum = 0;

        String sql = "SELECT MAX(CNUM) FROM BANKCLIENT";
        try {
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if(rs.next()){
                cNum = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cNum;
    }

    //계좌 번호 생성 메소드
    public String accountNumber() {
        String cAccount = null;

        // 신한은행 계좌번호 : 110-xxx(3자리)-xxxxxx(6자리)
        // 랜덤숫자 만들기 Math.random()
        // Math.random() ; 0.0000001 ~ 0.9999999까지
        // (Math.random() * 9) : 0.0000009 ~ 9.999999~8
        // (int)(Math.random() * 9) : 0~ 9 => 0부터 9사이의 숫자가 무작위로 생성

        // 110-
        cAccount = "110-";

        // 110-xxx
        // 반복문 3번 돌려서 0부터 9사이의 숫자를 추가(누적)
        for(int i=1; i<=3; i++){
            cAccount += (int)(Math.random() * 10);
        }

        // 110-xxx-
        cAccount += "-";

        // 110-xxx-xxxxxx
        for(int i=1; i<=6; i++){
            cAccount += (int)(Math.random() * 10);
        }

        // 우선 중복 걱정x
        return cAccount;
    }

    // 고객 가입 메소드
    public void joinClient(BankClient client) {
        // 회원가입과 동일
        try {
            String sql = "INSERT INTO BANKCLIENT VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, client.getcNum());
            pstmt.setString(2, client.getcName());
            pstmt.setString(3, client.getcAccount());
            pstmt.setInt(4, client.getBalance());
            
            int result = pstmt.executeUpdate();
            
            if(result > 0){
                System.out.println("가입 완료");
                System.out.println("고객번호: " + client.getcNum());
                System.out.println("계좌번호: " + client.getcAccount());
                System.out.println();
            }else{
                System.out.println("가입 실패");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean checkAccount(String cAccount) {
        boolean checked = false;
        String sql = "SELECT * FROM BANKCLIENT WHERE CACCOUNT = ?";
        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, cAccount);

            rs = pstmt.executeQuery();

            checked = rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return checked;
    }

    public void deposit(String cAccount, int amount) {
        String sql = "UPDATE BANKCLIENT SET BALANCE = BALANCE + ? WHERE CACCOUNT = ?";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, amount);
            pstmt.setString(2, cAccount);
            
            int result = pstmt.executeUpdate();
            
//            if(result > 0){
//                System.out.println("입금 완료");
//            }else{
//                System.out.println("입금 실패");
//            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void withdraw(String cAccount, int amount) {
        int balance;
        String sql = "UPDATE BANKCLIENT SET BALANCE = BALANCE - ? WHERE CACCOUNT = ?";
        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, amount);
            pstmt.setString(2, cAccount);

            int result = pstmt.executeUpdate();

//            if (result > 0){
//                System.out.println("출금 완료");
//            }else {
//                System.out.println("출금 실패");
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int checkBalance(String cAccount) {
        int balance = 0;
        String sql = "SELECT BALANCE FROM BANKCLIENT WHERE CACCOUNT = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cAccount);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                balance = rs.getInt(1);
//                System.out.println("잔   액: " + balance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return balance;
    }

    public void rem(int amount, String cAccount) {

    }
}
