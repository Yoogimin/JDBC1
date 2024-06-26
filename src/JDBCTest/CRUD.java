package JDBCTest;

import java.sql.*;

public class CRUD {
    /*
        C(Create) : 생성
        R(Read)   : 조회
        U(Update) : 수정
        D(Delete) : 삭제
     */

    // DB에 접속하기 위한 Connection 객체 con
    Connection con;

    // Java에서 작성한 sql문을 DB로 전달하기 위한 Statement 객체 stmt
    Statement stmt;

    // DB에서 실행된 결과를 저장하는 Result 객체 rs
    ResultSet rs;

    // [1] DB에 접속하기 위한 메소드
    public void connect(){
        con = DBConnection.DBConnect();
    }

    // [2] C : DB에 데이터를 저장하기 위한 메소드
    public void insert(){


        try {
            // (1) sql문을 작성하기 위한 화면 준비
            stmt = con.createStatement();

            // (2) 화면에 sql문 작성
            String sql = "INSERT INTO JDBC VALUES('Java', 17)";

            // (3) 실행 결과를 담기 위한 변수 result
            int result = stmt.executeUpdate(sql);

            // 실제 DB를 실행하는 함수 : stmt.executeUpdate(sql)
            // select를 제외한 insert, update, delete는 모두 stmt.executeUpdate(sql) 사용
            // 실핼하면 "1 행 이(가) 삽입되었습니다." 메세지가 나오는데 그 중 1값이 result에 저장

            // (4) 결과 처리
            if(result > 0){
                System.out.println("입력 성공");
            }else{
                System.out.println("입력 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // [3] R : DB에 있는 데이터를 조회하기 위한 메소드
    public void select(){
        
        try {
            // (1) 화면준비
            stmt = con.createStatement();

            // (2) sql문 작성
            String sql = "SELECT * FROM JDBC";

            // (3) 실행 결과를 담기 위한 변수 rs
            rs = stmt.executeQuery(sql);
            // 검색한 결과를 담기 위한 변수 rs(ResultSet 타입)
            // rs에 결과를 담을 때는 stmt.executeQuery(sql)을 사용
            
            // insert, update, delete => stmt.executeQuery(sql) :  int result
            
            // (4) 결과 처리
            while(rs.next()){
                System.out.println("DATA1 : " + rs.getString(1));
                System.out.println("DATA2 : " + rs.getInt(2));
                System.out.println();
            }
            // rs.next() : boolean타입 , 데이터의 갯수만큼 반복문을 실행
            // 데이터가 존재하면 true, 데이터가 존재하지 않으면 false
            // ex) 데이터가 5개 존재한다면 5번동안 true, 6번째부터 false값을 갖게 된다.
            
            // rs.getString(1) : 첫번째 컬럼의 값, 컬럼의 데이터가 문자열(NVARCHAR2)일때
            // rs.getInt(2)    : 두번째 컬럼의 값, 컬럼의 데이터가 숫자(NUMBER)일때
            // 컬럼의 데이터가 실수(소수점)일땐 rs.getDouble()을 사용
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

      
    }

    // [4] U : DB에 있는 데이터를 수정하기 위한 메소드
    public void update(){
        // (1) ~ (4)
        // String sql = "UPDATE JABC SET DATA2 = 19 WHERE DATA1 = 'ORACLE'";

        try {
            stmt = con.createStatement();

            String sql = "UPDATE JDBC SET DATA2 = 23 WHERE DATA1 = 'ORACLE'";

            int result = stmt.executeUpdate(sql);

            if(result > 0){
                System.out.println("수정 성공");
            }else{
                System.out.println("수정 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // [5] D : DB에 있는 데이터를 삭제하기 위한 메소드
    public void delete(){
        try {
            stmt = con.createStatement();

            String sql = "DELETE FROM JDBC WHERE DATA2 = 17";

            int result = stmt.executeUpdate(sql);

            if(result > 0){
                System.out.println("삭제 성공");
            }else{
                System.out.println("삭제 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // [6] DB 접속 해제
    public void conClose(){
        try {
            con.close();
            System.out.println("DB접속 해제");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectEMP(){
        try {
            stmt = con.createStatement();

            String sql = "SELECT * FROM EMP";

            rs = stmt.executeQuery(sql);

            while(rs.next()){
                System.out.print("ENO : " + rs.getInt(1));
                System.out.print(" | ENAME : " + rs.getString(2));
                System.out.print(" | JOB : " + rs.getString(3));
                System.out.print(" | MGR : " + rs.getString(4));
                System.out.print(" | HTREDTAE : " + rs.getString(5));
                System.out.print(" | SAL : " + rs.getString(6));
                System.out.print(" | COMM : " + rs.getString(7));
                System.out.print(" | DEPTNO: " + rs.getString(8));


                System.out.println();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
