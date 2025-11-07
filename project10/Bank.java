/* Bank.java
   main application: integrates users, admin tasks, and persistence.
*/
import java.io.*;
import java.util.*;

public class Bank implements HasMenu, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SAVE_FILE = "customers.ser";

    private final Admin admin = new Admin();
    private CustomerList customers = new CustomerList();

    public static void main(String[] args) {
        new Bank();
    }

    public Bank() {
        // loadSampleCustomers();
        // saveCustomers();

        loadCustomers();      // load if present, otherwise customers stays empty
        if (customers.isEmpty()) {
            loadSampleCustomers(); // helpful for first run
        }

        start();              // run the app
        saveCustomers();      // persist on exit
    }


    
    public String menu() {
        System.out.println("\nBank Menu\n");
        System.out.println("0) Exit system");
        System.out.println("1) Login as admin");
        System.out.println("2) Login as customer");
        System.out.print("\nAction: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    
    public void start() {
        while (true) {
            switch (menu()) {
                case "0": return;
                case "1": loginAsAdmin(); break;
                case "2": loginAsCustomer(); break;
                default:  System.out.println("Invalid choice.");
            }
        }
    }


    private void loginAsAdmin() {
        System.out.println("\nAdmin login");
        if (admin.login()) {
            startAdmin();
        }
    }

    // admin actions are implemented here per assignment
    public void startAdmin() {
        while (true) {
            String choice = admin.menu();
            switch (choice) {
                case "0": return;
                case "1": fullCustomerReport(); break;
                case "2": addUser(); break;
                case "3": applyInterest(); break;
                default:  System.out.println("Invalid choice.");
            }
        }
    }

    private void fullCustomerReport() {
        System.out.println("Full customer report");
        if (customers.isEmpty()) {
            System.out.println("(no customers)");
            return;
        }
        for (Customer c : customers) {
            System.out.println(c.getReport());
        }
    }

    private void addUser() {
        System.out.println("Add User");
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("PIN: ");
        String pin = sc.nextLine().trim();

        // prevent duplicates by username
        for (Customer c : customers) {
            if (c.getUserName().equals(name)) {
                System.out.println("A user with that name already exists.");
                return;
            }
        }

        customers.add(new Customer(name, pin));
        System.out.println("User created.");
    }

    private void applyInterest() {
        System.out.println("Apply interest");
        if (customers.isEmpty()) return;

        for (Customer c : customers) {
            double newBal = c.applyInterestToSavings();
            System.out.println("New balance: " + String.format("$%.2f", newBal));
        }
    }


    private void loginAsCustomer() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("User name: ");
        String u = sc.nextLine().trim();
        System.out.print("PIN: ");
        String p = sc.nextLine().trim();

        Customer current = null;
        for (Customer c : customers) {
            if (c.login(u, p)) { // reuse User.login(user, pin)
                current = c;
                break;
            }
        }
        if (current == null) {
            System.out.println("Login Failed");
            return;
        }
        // Customer.start() already manages its own menus (checking/savings)
        current.start();
    }

    public void loadSampleCustomers() {
        customers.clear();
        Customer alice = new Customer("Alice", "1111");
        alice.checking.setBalance(1000.0);
        alice.savings.setBalance(1000.0);
        alice.savings.setInterestRate(0.05); // 5% to match sample math ($1050.00)

        Customer bob   = new Customer("Bob",   "2222");
        Customer cindy = new Customer("Cindy", "3333");

        customers.add(alice);
        customers.add(bob);
        customers.add(cindy);
        System.out.println("Sample customers loaded.");
    }

    public void saveCustomers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(customers);
            System.out.println("Saved " + customers.size() + " customers.");
        } catch (IOException e) {
            System.out.println("Failed to save customers: " + e.getMessage());
        }
    }

    public void loadCustomers() {
        File f = new File(SAVE_FILE);
        if (!f.exists()) {
            System.out.println("No saved customers found.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof CustomerList) {
                customers = (CustomerList) obj;
                System.out.println("Loaded " + customers.size() + " customers.");
            } else {
                System.out.println("Save file format not recognized. Starting fresh.");
            }
        } catch (Exception e) {
            System.out.println("Failed to load customers: " + e.getMessage());
        }
    }
}