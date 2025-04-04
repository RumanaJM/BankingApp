public class SmallBusinessAccount extends BankAccount {
    private final double overdraftFacility = 1000.0;

    public SmallBusinessAccount(String accountName, String accountNumber, double balance) {
        super(accountName, accountNumber, 0.0);
    }

    @Override
    public double getOverdraftFacility() {
        return overdraftFacility;
    }

    
}
