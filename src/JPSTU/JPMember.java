package JPSTU;

public class JPMember {
    // 필드
    private int jNum;
    private String jPw;
    private String jName;
    private String jAge;
    private String jGender;
    private String jEmail;
    private String jPhone;
    // 생성자
    // 기본생성자 생략가능
    public JPMember() {}

    // 메소드
    // getter, setter, toString
    public int getjNum() {
        return jNum;
    }

    public void setjNum(int jNum) {
        this.jNum = jNum;
    }

    public String getjPw() {
        return jPw;
    }

    public void setjPw(String jPw) {
        this.jPw = jPw;
    }

    public String getjName() {
        return jName;
    }

    public void setjName(String jName) {
        this.jName = jName;
    }

    public String getjAge() {
        return jAge;
    }

    public void setjAge(String jAge) {
        this.jAge = jAge;
    }

    public String getjGender() {
        return jGender;
    }

    public void setjGender(String jGender) {
        this.jGender = jGender;
    }

    public String getjEmail() {
        return jEmail;
    }

    public void setjEmail(String jEmail) {
        this.jEmail = jEmail;
    }

    public String getjPhone() {
        return jPhone;
    }

    public void setjPhone(String jPhone) {
        this.jPhone = jPhone;
    }

    //---------------------------------------------------------------------
    @Override
    public String toString() {
        return "JPMember{" +
                "jNum=" + jNum +
                ", jPw='" + jPw + '\'' +
                ", jName='" + jName + '\'' +
                ", jAge='" + jAge + '\'' +
                ", jGender='" + jGender + '\'' +
                ", jEmail='" + jEmail + '\'' +
                ", jPhone='" + jPhone + '\'' +
                '}';
    }

}
