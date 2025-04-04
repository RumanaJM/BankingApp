import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private final ArrayList<BankAccount> accounts;

    public Main() {
        accounts = new ArrayList<>();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

               

        // Display options to user
        System.out.println("Welcome to the Business ATM.");
        System.out.println("1. Login");
        System.out.println("2. Create account");

        // Get user input
        int choice = scanner.nextInt();
        scanner.nextLine();

        // Handle user input
        if (choice == 1) {
            // Login
            loggedIn = login(scanner);
        } 

        // Continue with ATM functionality if user is logged in
        if (loggedIn) {
            Main atm = new Main();

            while (true) {
                System.out.println("Welcome to the Business ATM system!");
                System.out.println("Please select an option:");
                System.out.println("1. Add an account");
                System.out.println("2. Deposit money");
                System.out.println("3. Withdraw money");
                System.out.println("4. Transfer money ***to transfer money you must add two accounts first. They " +
                        "will be named account 1 and account 2***");
                System.out.println("5. Check balance");
                System.out.println("6. List all accounts");
                System.out.println("7. Exit");
                //System.out.println("8. Create a new user account");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {

                    case 1:
                        System.out.println("Please select an account type:");
                        System.out.println("1. Small business account");
                        System.out.println("2. Community account");
                        System.out.println("3. Client account");

                        int accountType = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Please enter the account name:");
                        String accountName = scanner.nextLine();

                        BankAccount newAccount = null;
                        
                        switch (accountType) {
                            case 1:
                                newAccount = new SmallBusinessAccount(accountName, Integer.toString(atm.accounts.size()
                                        + 1), accountType);
                                        //Displaying the account name and number after creation
                                        System.out.println("Account Name: " + newAccount.getAccountName() + ", Account Number: " + newAccount.getAccountNumber());
                                break;
                            case 2:
                                newAccount = new CommunityAccount(accountName, Integer.toString(atm.accounts.size() + 1),
                                        accountType);
                                System.out.println("Account Name: " + newAccount.getAccountName() + ", Account Number: " + newAccount.getAccountNumber());
                                break;
                            case 3:
                                newAccount = new ClientAccount(accountName, Integer.toString(atm.accounts.size() + 1),
                                        accountType);
                                System.out.println("Account Name: " + newAccount.getAccountName() + ", Account Number: " + newAccount.getAccountNumber());
                                break;
                            default:
                                System.out.println("Invalid account type!");
                                break;
                        }

                        if (newAccount != null) {
                            atm.addAccount(newAccount);
                            System.out.println("Account added successfully!");
                            System.out.println(newAccount);
                        }
                        break;

                    case 2:
                        System.out.println("Please enter the account number:");
                        String depositAccountNumber = scanner.nextLine();

                        System.out.println("Please enter the deposit amount:");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine();

                        boolean depositSuccess = atm.deposit(depositAccountNumber, depositAmount);

                        if (depositSuccess) {
                            System.out.println("Deposit successful!");
                            System.out.println("New balance: " + atm.checkBalance(depositAccountNumber));
                        } else {
                            System.out.println("Deposit failed! Account not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Please enter the account number:");
                        String withdrawAccountNumber = scanner.nextLine();

                        System.out.println("Please enter the withdrawal amount:");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine();

                        boolean withdrawSuccess = atm.withdraw(withdrawAccountNumber, withdrawAmount);

                        if (withdrawSuccess) {
                            System.out.println("Withdrawal successful!");
                            System.out.println("New balance: " + atm.checkBalance(withdrawAccountNumber));
                        } else {
                            System.out.println("Withdrawal failed! Account not found or insufficient balance.");
                        }
                        break;


                    case 4:
                        System.out.println("Please enter the account number for account 1:");
                        String account1Number = scanner.nextLine();
                        System.out.println("Please enter the account number for account 2:");
                        String account2Number = scanner.nextLine();
                        BankAccount account1 = atm.findAccount(account1Number);
                        BankAccount account2 = atm.findAccount(account2Number);

                        if (account1 == null || account2 == null) {
                            System.out.println("One or both accounts not found!");
                        } else {
                            System.out.println("Please enter the transfer amount:");
                            double transferAmount = scanner.nextDouble();
                            scanner.nextLine();

                            boolean transferSuccess = account1.transfer(account2, transferAmount);

                            if (transferSuccess) {
                                System.out.println("Transfer successful!");
                                System.out.println("New balance for account " + account1Number + ": "
                                        + atm.checkBalance(account1Number));
                                System.out.println("New balance for account " + account2Number + ": "
                                        + atm.checkBalance(account2Number));

                            } else {
                                System.out.println("Transfer failed! Insufficient funds.");
                            }
                        }
                        break;


                    case 5:
                        System.out.println("Please enter the account number:");
                        String balanceAccountNumber = scanner.nextLine();

                        double balance = atm.checkBalance(balanceAccountNumber);

                        if (balance != -1) {
                            System.out.println("Balance of account " + balanceAccountNumber + ": " + balance);
                        } else {
                            System.out.println("Balance check failed! Account not found.");
                        }
                        break;

                    

                    case 6: // List all accounts
                    //System.out.println("Existing accounts:");
                    if(atm.accounts == null || atm.accounts.isEmpty()) {
                        System.out.println("No existing accounts");
                    }
                    else {
                        System.out.println("Existing accounts:");
                        // Loop through the list of accounts and print their details
                        System.out.println("Existing accounts:");
                    }
                    for (BankAccount acc  : atm.accounts) {
                            
                            System.out.println("Account Name: " + acc.getAccountName() + ", Account Number: " + acc.getAccountNumber());
                        }
                        break;
                        
                    case 7:
                    System.out.println("Exiting the Business ATM system. Thank you for using our services!");
                    System.exit(0);
                    break;

                    default:
                        System.out.println("Invalid option! Please select a valid option.");
                        break;
                        
                }
            }
        }
    }
//updated the login method to validate username before asking for password
private static boolean login(Scanner scanner) {
    System.out.println("Please enter your username: ***Cheat answer is myusername***");
    String username = scanner.nextLine();

    if (!username.equals("myusername")) {
        System.out.println("Invalid username. Please try again.");
        return false; // Immediately exits if username is incorrect
    }

    System.out.println("Please enter your password: ***Cheat answer is mypassword***");
    String password = scanner.nextLine();

    if (password.equals("mypassword")) {
        System.out.println("Login successful!");
        return true;
    } else {
        System.out.println("Invalid password. Please try again.");
        return false;
    }
}

    private static void createUserAccount(String username, String password) {
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public boolean deposit(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount); // The BankAccount class handles overdraft logic
            return true; // Assume withdrawal is successful if no exception is thrown
        }
        return false;
    }

    public double checkBalance(String accountNumber) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            return account.getBalance();
        }
        return -1;
    }

    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        System.out.println("Account not found: " + accountNumber);
        return null;
    }
}
