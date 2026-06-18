public class Account {

    private int accountNo;
    private String holderName;
    private String accountType;
    private double balance;

    public Account(){}

    public Account(String holderName,
                   String accountType,
                   double balance){

        this.holderName=holderName;
        this.accountType=accountType;
        this.balance=balance;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }
}