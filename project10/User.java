/* User.java
    abstract class representing a user with login functionality. 
    implements HasMenu interface.
*/

import java.io.Serializable;
import java.util.Scanner;

public abstract class User implements HasMenu, Serializable { // abstract user class
    protected String userName; // username
    protected String PIN; // user pin

    public boolean login() { // prompt for login
        Scanner sc = new Scanner(System.in); // scanner for input
        System.out.print("User name:"); 
        String u = sc.nextLine().trim();
        System.out.print("PIN:");
        String p = sc.nextLine().trim();
        return login(u, p);
    }

    public boolean login(String userName, String PIN) { // check login
        boolean ok = this.userName != null // check username and pin
                && this.userName.equals(userName) // match
                && this.PIN != null // check pin
                && this.PIN.equals(PIN); // match
        if (ok) System.out.println("Login Successful"); 
        else System.out.println("Login Failed"); 
        return ok;
    }

    public void setUserName(String userName) { this.userName = userName; } // setter
    public String getUserName() { return userName; } // getter

    public void setPIN(String PIN) { this.PIN = PIN; } // setter
    public String getPIN() { return PIN; } // getter

    public abstract String getReport(); // abstract method for report
}