# CS121 Project 10
Bank on it Part 2

# Algorithm

* Goal(this week):
  * Build rest of project

## Interface
* HasMenu
  * String menu() ; print options, return prompt text
  * void start() ; loop until user chooses Exit/Quit



## CheckingAccount
* Public:
  * CheckingAccount() 
  * double getBalance() 
  * String getBalanceString() 
  * void checkBalance() 
  * void makeDeposit() 
  * void makeWithdrawal() 
* String menu() ; 
  Account menu
  0) quit
  1) check balance
  2) deposit
  3) withdraw
* void start() ; read choice

## SavingsAccount (extends CheckingAccount)

* Public:
  * void setInterestRate
  * double getInterestRate
  * void calcInterest() 

## 4) User (abstract, implements HasMenu)

* Public:
  * boolean login() 
  * boolean login(String u, String p) 
  * getters/setters for userName, PIN
  * abstract String getReport()

## 5) Customer (extends User)

* Constructors:
  * Default ; userName="Alice", PIN="0000", zeroed accounts

* Menu (menu()):
Customer Menu
0) Exit
1) Manage Checking
2) Manage Savings
3) Change PIN

* Start (start()):
1) Run login() ; if false ; print "Login failed" and return
2) Loop on menu:
   - 1 ; checking.start()
   - 2 ; savings.start()
   - 3 ; prompt new PIN 
   - 0 ; exit

Report (getReport()):  
"Customer: <name>\nChecking: " + checking.getBalanceString() + "\nSavings: " + savings.getBalanceString()

## New Additions
* Create Customer List
* Create Bank
* Create admin login and admin functions
