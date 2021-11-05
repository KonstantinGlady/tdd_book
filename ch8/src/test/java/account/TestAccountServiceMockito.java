package account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TestAccountServiceMockito {

    @Mock
    AccountManager mockitoManager;

    @Test
    public void testGetTransferOk() {
        Account sender = new Account("1", 200);
        Account beneficiary = new Account("2", 100);

        Mockito.lenient().when(mockitoManager.findAccountForUser("1")).thenReturn(sender);
        Mockito.lenient().when(mockitoManager.findAccountForUser("2")).thenReturn(beneficiary);

        AccountService service = new AccountService();
        service.setAccountManager(mockitoManager);
        service.transfer("1", "2", 50);

        assertEquals(150, sender.getBalance());
        assertEquals(150, beneficiary.getBalance());
    }

}