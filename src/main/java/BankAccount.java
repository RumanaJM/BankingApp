
public class BankAccount  {
    private String accountName;
    private String accountNumber;
    private double balance;


    public BankAccount(String accountName, String accountNumber, double balance) {
        super();
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        System.out.println("Account created: " + accountName + ", Balance: " + balance); // Debug statement
    }
    public BankAccount(String accountName, String accountNumber) {
        this(accountName, accountNumber, 0.0); // Default balance is 0.0
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        System.out.println("Depositing amount: " + amount); // Debug statement
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (balance - amount >= -getOverdraftFacility()) { // Use account-specific overdraft limit
            balance -= amount;
            System.out.println("Withdrawal successful! New balance: " + balance);
        } else {
            System.out.println("Insufficient funds! Overdraft limit exceeded.");
        }
    }

    public boolean transfer(BankAccount toAccount, double amount) {
        if (balance - amount >= 0) {
            balance -= amount;
            toAccount.deposit(amount);
        } else {
            System.out.println("Insufficient funds!");
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean TwoSignatoriesRestriction(String firstSignatory) {
        return false;
    }

    // Method to get overdraft facility
    public double getOverdraftFacility() {
        return 0.0; // Example overdraft limit
    }


}


