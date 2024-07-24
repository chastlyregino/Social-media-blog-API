package Service;

import Model.Account;
import DAO.AccountDAO;

import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public List<Account> getAllAccounts() {
        return this.accountDAO.getAllAccounts();
    }

    public Account addAccount(Account account) {
        if (!account.getUsername().isEmpty()
            && account.getPassword().length() > 3
            && this.accountDAO.getAccountByUsername(account.getUsername()) == null) {
                return this.accountDAO.createNewAccount(account);
        }
        return null;
    }

    public Account loginAccount(Account account) {
        Account userCredentials = this.accountDAO.getAccountByUsername(account.getUsername());
        if ((userCredentials != null)
        && userCredentials.getPassword().equalsIgnoreCase(account.getPassword())) {
            return userCredentials;
        }
        return null;
    }
}

