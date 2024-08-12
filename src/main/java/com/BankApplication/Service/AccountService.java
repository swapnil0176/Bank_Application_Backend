package com.BankApplication.Service;


import java.util.List;

import com.BankApplication.Model.AccountDTO;

public interface AccountService {
	AccountDTO createAccount(AccountDTO accountDTO);
	AccountDTO getAccountInfo(Long id);
	AccountDTO depositeAmount(Long id,double balance);
	AccountDTO withdrawAmount(Long id,double balance);
	List<AccountDTO> getAllAccounts();
}
