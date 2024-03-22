package JPSTU;

import java.sql.*;

public class JPSQL {

    Connection con;     // DB접속
    //Statement stmt;     // SQL문 작성
    ResultSet rs;       // DB결과

    // SQL문을 DB로 전달하기 위한 객체
    // '?'를 데이터로 인식
    // Statement stmt 대신 사용
    PreparedStatement pstmt;

    // [1] DB접속
    public void connect(){
        con = DBC.DBConnect();
    }

    // [2] DB해제
    public void conClose(){
        try {
            con.close();
            System.out.println("DB접속 해제");
        } catch (SQLException e) {
            throw new RuntimeException(e);
            // throw : 던지다
            // RuntimeException(e) : 더이상 프로그램이 동작하지 않게 한다
        }
    }

    // [3] 학생등록
    public void memberJoin(JPMember member){
        // (1) SQL문 작성
        String sql ="INSERT INTO JPSTU VALUES(?,?,?,?,?,?,?)";

        try {
            // (2) 화면구성
            pstmt = con.prepareStatement(sql);

            // (3) '?'에 데이터 입력
            pstmt.setInt(1, member.getjNum());
            pstmt.setString(2, member.getjPw());
            pstmt.setString(3, member.getjName());
            pstmt.setString(4, member.getjAge());
            pstmt.setString(5, member.getjGender());
            pstmt.setString(6, member.getjEmail());
            pstmt.setString(7, member.getjPhone());

            // (4) 실행
            int result = pstmt.executeUpdate();

            // (5) 결과
            if(result > 0){
                System.out.println("학생등록 성공");
            } else {
                System.out.println("학생등록 실패");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // (2_2) '?'에 데이터 입력
        // (3) 실행
        // (4) 결과

    }

    // [4] 학생목록
    public void memberlist() {

        try {
            String sql = "SELECT * FROM JPSTU"; // 이거 순서 기억해야 된다. 
            pstmt = con.prepareStatement(sql);  // 이게 먼저 하면안되 try는 하되 순서는 String sql이 먼저야
            rs = pstmt.executeQuery();
            while (rs.next()){
                System.out.print("번호: " + rs.getInt(1));
                System.out.print("\t| 비번: " + rs.getString(2));
                System.out.print("\t| 이름: " + rs.getString(3));
                System.out.print("\t| 나이: " + rs.getString(4));
                System.out.print("\t| 성별: " + rs.getString(5));
                System.out.print("\t| 메일: " + rs.getString(6));
                System.out.print("\t| 폰번: " + rs.getString(7));
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // [*] 번호, 비밀번호 체크
    public boolean idCheck(int jNum, String jPw) {
        boolean result = false;


        try {
            // (1)SQL문 작석
            String sql = "SELECT * FROM JPSTU WHERE JNUM = ? AND JPW = ?";

            // (2) 화면구성
            pstmt = con.prepareStatement(sql);

            // (3) 데이터 입력 => ? 대신에 데이터를 넣어준다.
            pstmt.setInt(1, jNum);
            pstmt.setString(2, jPw);

            // (4) 실행
            rs = pstmt.executeQuery(); // select는 무저건 (rs)

            // (5)결과
            // rs.next() : 데이터가 있으면 true, 없으면 false
            result = rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    // [5] 회원수정
    public void memberModify(JPMember member) {
        try {
            // (1) sql문
            String sql = "UPDATE JPSTU SET JPW=?, JNAME=?,JAGE=?,JGENDER=?,JEMAIL=?,JPHONE=? WHERE JNUM=?";

            // (2) 화면구성
            pstmt = con.prepareStatement(sql);

            // (3) 데이터 입력
            pstmt.setString(1, member.getjPw());
            pstmt.setString(2, member.getjName());
            pstmt.setString(3, member.getjAge());
            pstmt.setString(4, member.getjGender());
            pstmt.setString(5, member.getjEmail());
            pstmt.setString(6, member.getjPhone());
            pstmt.setInt(7, member.getjNum());

            // (4) 실행
            int result = pstmt.executeUpdate();

            // (5)결과
            if(result > 0){
                System.out.println("수정 성공");
            }else{
                System.out.println("수정 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // [6] 회원삭제
    public void memberDelete(int jNum) {
        try {
            // (1) sql문
            String sql = "DELETE FROM JPSTU WHERE JNUM=?";

            // (2) 화면구성
            pstmt = con.prepareStatement(sql);

            // (3) 데이터 입력
            pstmt.setInt(1, jNum);

            // (4) 실행
            int result = pstmt.executeUpdate();

            // (5)결과
            if (result > 0) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
