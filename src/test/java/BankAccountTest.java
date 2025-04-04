

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {
    private BankAccount account;

    @Before
    public void setUp() {
        account = new BankAccount("John Smith", "1234567890", 1000.0);
    }

    @Test
    public void testDeposit() {
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance(), 0.01);
    }
//Added extra test cases for depositing and withdrawing negative amounts and zero amounts
    @Test
public void testDepositZero() {
    account.deposit(0.0);
    assertEquals(1000.0, account.getBalance(), 0.01);
}

@Test
public void testWithdrawZero() {
    account.withdraw(0.0);
    assertEquals(1000.0, account.getBalance(), 0.01);
}

@Test
public void testDepositNegativeAmount() {
    account.deposit(-500.0);
    assertEquals(1000.0, account.getBalance(), 0.01);
}

@Test
public void testWithdrawNegativeAmount() {
    account.withdraw(-500.0);
    assertEquals(1000.0, account.getBalance(), 0.01);
}

    @Test
    public void testWithdraw() {
        account.withdraw(500.0);
        assertEquals(500.0, account.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        account.withdraw(1500.0);
        assertEquals(1000.0, account.getBalance(), 0.01);
    }

    @Test
    public void testTransfer() {
        BankAccount toAccount = new BankAccount("Jane Doe", "0987654321", 500.0);
        account.transfer(toAccount, 500.0);
        assertEquals(500.0, account.getBalance(), 0.01);
        assertEquals(1000.0, toAccount.getBalance(), 0.01);
    }

    @Test
    public void testTransferInsufficientFunds() {
        BankAccount toAccount = new BankAccount("Jane Doe", "0987654321", 500.0);
        account.transfer(toAccount, 1500.0);
        assertEquals(1000.0, account.getBalance(), 0.01);
        assertEquals(500.0, toAccount.getBalance(), 0.01);
    }

    @Test
    public void testGetAccountName() {
        assertEquals("John Smith", account.getAccountName());
    }

    @Test
    public void testGetAccountNumber() {
        assertEquals("1234567890", account.getAccountNumber());
    }

    @Test
    public void testTwoSignatoriesRestriction() {
        assertFalse(account.TwoSignatoriesRestriction("John Smith"));
    }
}
