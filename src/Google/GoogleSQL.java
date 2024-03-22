package Google;

import java.sql.*;

public class GoogleSQL {

    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public void connect() {
        con = DBC.DBConnect();
    }

    public void conClose() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void memberJoin(GoogleMember member) {
        String sql = "INSERT INTO GOOGLE VALUES(?,?,?,?,?,?,?)";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, member.getgId());
            pstmt.setString(2, member.getgPw());
            pstmt.setString(3, member.getgEmail());
            pstmt.setString(4, member.getgName());
            pstmt.setString(5, member.getgBirth());
            pstmt.setString(6, member.getgGender());
            pstmt.setString(7, member.getgPhone());

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("회원가입 성공");
            } else {
                System.out.println("회원가입 실패");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void memberList() {
        String sql = "SELECT * FROM GOOGLE";

        try {
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.print("|아이디: " + rs.getString(1));
                System.out.print("\t|이  름: " + rs.getString(4));
                System.out.println("\t|연락처: " + rs.getString(7));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean memberLogin(String gId, String gPw) {
        boolean result = false;
        String sql = "SELECT * FROM GOOGLE WHERE GID=? AND GPW=?";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, gId);
            pstmt.setString(2, gPw);

            rs = pstmt.executeQuery();

            result = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    public void myInfo(String gId) {
        String sql = "SELECT GID, GPW, GEMAIL, GNAME, TO_CHAR(GBIRTH, 'YYYY-MM-DD'), GGENDER, GPHONE FROM GOOGLE WHERE GID=?";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, gId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("\t|아이디: " + rs.getString(1));
                System.out.println("\t|비밀번호: " + rs.getString(2));
                System.out.println("\t|이메일: " + rs.getString(3));
                System.out.println("\t|이  름: " + rs.getString(4));
                System.out.println("\t|생년월일: " + rs.getString(5));
                System.out.println("\t|성  별: " + rs.getString(6));
                System.out.println("\t|연락처: " + rs.getString(7));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void memberModify(GoogleMember member) {
        try {

            String sql = "UPDATE GOOGLE SET GPW=?, GEMAIL=?, GNAME=?, GBIRTH=?, GGENDER=?, GPHONE=? WHERE GID=?";

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, member.getgPw());
            pstmt.setString(2, member.getgEmail());
            pstmt.setString(3, member.getgName());
            pstmt.setString(4, member.getgBirth());
            pstmt.setString(5, member.getgGender());
            pstmt.setString(6, member.getgPhone());
            pstmt.setString(7, member.getgId());


            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("수정 완료");
            } else {
                System.out.println("수정 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void myInfoDelete(String gId, String gPw) {
        String sql = "DELETE FROM GOOGlE WHERE GID=? AND GPW=?";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, gId);
            pstmt.setString(2, gPw);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("탈퇴 완료");
            } else {
                System.out.println("탈퇴 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

