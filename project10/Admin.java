/* Admin.java
   admin user for the banking console.
*/
import java.util.Scanner;

public class Admin extends User { 
    public Admin() {
        this.userName = "admin";
        this.PIN = "0000";
    }

    // admin menu text is defined here, but actions are handled by Bank.startAdmin()
    public String menu() {
        System.out.println("\nAdmin Menu\n");
        System.out.println("0) Exit this menu");
        System.out.println("1) Full customer report");
        System.out.println("2) Add user");
        System.out.println("3) Apply interest to savings accounts");
        System.out.print("\nAction: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    // Intentionally empty per assignment; Bank manages admin actions.
    public void start() { }

    public String getReport() { 
        return "Admin: " + userName + " (PIN: " + PIN + ")";
    }
}