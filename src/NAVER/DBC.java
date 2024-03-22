package NAVER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC {
    public static Connection DBConnect(){

        Connection con = null;

        String user = "GI";
        String password = "1111";

        String url = "jdbc:oracle:thin:@localhost:1521:XE";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            con = DriverManager.getConnection(url,user, password);

            //System.out.println("DB접속 성공");

        } catch (ClassNotFoundException e) {
            System.out.println("DB접속 실패 : 드라이버 로딩 실패");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("DB접속 실패: 접속 정보 오류");
            throw new RuntimeException(e);
        }

        return con;
    }


}
