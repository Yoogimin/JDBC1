package BANK;

public class BankClient {

    private int cNum;       // 고객번호
    private String cName;   // 고객이름
    private String cAccount;// 계좌번호
    private int balance;    // 계좌잔액

    public BankClient() {}

    public int getcNum() {
        return cNum;
    }

    public void setcNum(int cNum) {
        this.cNum = cNum;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAccount() {
        return cAccount;
    }

    public void setcAccount(String cAccount) {
        this.cAccount = cAccount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankClient{" +
                "cNum=" + cNum +
                ", cName='" + cName + '\'' +
                ", cAccount='" + cAccount + '\'' +
                ", balance=" + balance +
                '}';
    }
}
