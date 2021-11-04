package account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class TestAccountServiceEasyMock {

    private AccountManager mockAccountManager;

    @BeforeEach
    public void setUp() {
        mockAccountManager = createMock("MockAccountManager", AccountManager.class);
    }

    @Test
    public void testTransferOk() {
        Account sender = new Account("1", 200);
        Account beneficiary = new Account("2", 100);

        mockAccountManager.updateAccount(sender);
        mockAccountManager.updateAccount(beneficiary);

        expect(mockAccountManager.findAccountForUser("1")).andReturn(sender);
        expect(mockAccountManager.findAccountForUser("2")).andReturn(beneficiary);
        replay(mockAccountManager);//после подготовки мока запукаем этой командой

        AccountService service = new AccountService();
        service.setAccountManager(mockAccountManager);
        service.transfer("1", "2", 50);

        assertEquals(150, sender.getBalance());
        assertEquals(150, beneficiary.getBalance());
    }

    @AfterEach
    public void tearDown() {
        verify(mockAccountManager);// проверяем что вызов мока был
    }
}