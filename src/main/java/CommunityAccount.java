public class CommunityAccount extends BankAccount {
    private double overdraftFacility = 2500.0;

    public CommunityAccount(String accountName, String accountNumber, double balance) {
        super(accountName, accountNumber, 0.0);
    }

    @Override
    public double getOverdraftFacility() {
        return overdraftFacility;
    }
}
