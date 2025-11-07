/* SavingsAccount.java
   represents a savings account that extends CheckingAccount and adds interest.
*/
public class SavingsAccount extends CheckingAccount {
    private static final long serialVersionUID = 1L;

    private double interestRate;

    public SavingsAccount() { this(0.0d, 0.0d); }

    public SavingsAccount(double balance, double interestRate) { 
        super(balance);
        this.interestRate = interestRate;
    }

    public static void main(String[] args) {
        SavingsAccount s = new SavingsAccount(0.0d, 0.01d);
        s.start();
    }

    public void calcInterest() { // interest
        double interest = balance * interestRate;
        balance += interest;
    }

    public void setInterestRate(double rate) { this.interestRate = rate; }

    public double getInterestRate() { return interestRate; }

    public void start() {
        System.out.println("Savings Account");
        super.start();
    }
}