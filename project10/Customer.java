/* Customer.java
   used to manage a customer's checking and savings accounts.
*/
import java.io.Serializable;
import java.util.Scanner;

public class Customer extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    CheckingAccount checking;
    SavingsAccount savings;

    public static void main(String[] args) {
        Customer c = new Customer("Alice", "1111");
        c.start();
    }

    public Customer() { this("Alice", "1111"); }

    public Customer(String userName, String PIN) {
        this.userName = userName;
        this.PIN = PIN;
        this.checking = new CheckingAccount(0.0d);
        this.savings  = new SavingsAccount(0.0d, 0.01d); // default 1%
    }

    public void start() {
        if (!login()) return;

        outer:
        while (true) {
            System.out.println("\nCustomer Menu\n");
            System.out.println("0) Exit");
            System.out.println("1) Manage Checking Account");
            System.out.println("2) Manage Savings Account");
            System.out.println("3) change PIN");
            String action = menu();
            switch (action) {
                case "0": break outer;
                case "1": checking.start(); break;
                case "2": savings.start(); break;
                case "3": changePIN(); break;
                default:  System.out.println("Invalid choice.");
            }
        }
    }

    public String menu() {
        System.out.print("\nAction (0-3): ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    public void changePIN() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new PIN: ");
        String np = sc.nextLine().trim();
        this.PIN = np;
        System.out.println("PIN changed.");
    }

    // helper for Bank.applyInterest()
    public double applyInterestToSavings() {
        savings.calcInterest();
        return savings.getBalance();
    }

    // report format required by the assignment sample
    public String getReport() {
        return String.format(
            "User: %s, Checking: %s, Savings: %s",
            userName, checking.getBalanceString(), savings.getBalanceString()
        );
    }
}