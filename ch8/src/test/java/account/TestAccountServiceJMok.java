package account;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.*;

class TestAccountServiceJMok {

    @RegisterExtension
    Mockery context = new JUnit5Mockery();
    private AccountManager mockAccountManager;

    @BeforeEach
    public void setUp() {
        mockAccountManager = context.mock(AccountManager.class);
    }


    @Test
    public void testContentOk() {
        Account sender = new Account("1", 200);
        Account beneficiary = new Account("2", 100);

        context.checking(new Expectations() {
            {
                oneOf(mockAccountManager).findAccountForUser("1");
                will(returnValue(sender));
                oneOf(mockAccountManager).findAccountForUser("2");
                will(returnValue(beneficiary));

                oneOf(mockAccountManager).updateAccount(sender);
                oneOf(mockAccountManager).updateAccount(beneficiary);
            }
        });

        AccountService service = new AccountService();
        service.setAccountManager(mockAccountManager);
        service.transfer("1", "2", 50);

        assertEquals(150, sender.getBalance());
        assertEquals(150, beneficiary.getBalance());
    }

}